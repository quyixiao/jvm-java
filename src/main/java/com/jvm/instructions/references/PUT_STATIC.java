package com.jvm.instructions.references;

import com.jvm.instructions.base.ClassInitLogic;
import com.jvm.instructions.base.Index16Instruction;
import com.jvm.rtda.Frame;
import com.jvm.rtda.OperandStack;
import com.jvm.rtda.heap.*;
import com.jvm.utils.ExceptionUtils;

// Set static field in class
public class PUT_STATIC extends Index16Instruction {
    @Override
    public void Execute(Frame frame) {
        JMethod currentMethod = frame.Method();
        JClass currentClass = currentMethod.classMember.Class();
        JConstantPool cp = currentClass.ConstantPool();
        FieldRef fieldRef = (FieldRef) cp.GetConstant(this.Index);
        JField field = fieldRef.ResolvedField();
        //先拿到当前方法、当前类和当前常量池，然后解析字段符号引
        //用。如果声明字段的类还没有被初始化，则需要先初始化该类
        JClass jClass = field.classMember.Class();
        //先拿到当前方法、当前类和当前常量池，然后解析字段符号引用。如果声明字段的类还没有被初始化，则需要先初始化该类
        if (!jClass.InitStarted() ){
            frame.RevertNextPC();
            ClassInitLogic.InitClass(frame.Thread(), jClass);
            return;
        }

        //如果解析后的字段是实例字段而非静态字段，则抛出 IncompatibleClassChangeError异常。
        if (!field.classMember.IsStatic()) {
            ExceptionUtils.throwException("java.lang.IncompatibleClassChangeError");
        }

        //如果是final字段，则实际操作的 是静态常量，只能在类初始化方法中给它赋值。否则，会抛出 IllegalAccessError异常。类初始化方法由编译器生成，名字是 <clinit>
        if (field.classMember.IsFinal()) {
            if (currentClass != jClass || !currentMethod.classMember.Name() .equals("<clinit>")) {
                ExceptionUtils.throwException("java.lang.IllegalAccessError");
            }
        }

        String descriptor = field.classMember.Descriptor();
        int slotId = field.SlotId();
        Slots slots = jClass.StaticVars();
        OperandStack stack = frame.OperandStack();
        //根据字段类型从操作数栈中弹出相应的值，然后赋给静态变量。
        char c[] = descriptor.toCharArray();
        switch (c[0]) {
            case 'Z':
            case 'B':
            case 'C':
            case 'S':
            case 'I':
                slots.SetInt(slotId, stack.PopInt());
                break;
            case 'F':
                slots.SetFloat(slotId, stack.PopFloat());
                break;
            case 'J':
                slots.SetLong(slotId, stack.PopLong());
                break;
            case 'D':
                slots.SetDouble(slotId, stack.PopDouble());
                break;
            case 'L':
            case '[':
                slots.SetRef(slotId, stack.PopRef());
                break;
            default:

        }
    }
}
