package com.jvm.instructions.references;

import com.jvm.instructions.base.Index16Instruction;
import com.jvm.rtda.Frame;
import com.jvm.rtda.OperandStack;
import com.jvm.rtda.heap.*;
import com.jvm.utils.ExceptionUtils;

// Set field in object
public class PUT_FIELD extends Index16Instruction {
    @Override
    public void Execute(Frame frame) {
        JMethod currentMethod = frame.Method();
        JClass currentClass = currentMethod.classMember.Class();
        JConstantPool cp = currentClass.ConstantPool();
        FieldRef fieldRef = (FieldRef) cp.GetConstant(this.Index);
        JField field = fieldRef.ResolvedField();
//第一，解析后的字段必须是实例字段，否则抛出 IncompatibleClassChangeError。
        if (field.classMember.IsStatic()) {
            ExceptionUtils.throwException("java.lang.IncompatibleClassChangeError");
        }
        //第二，如果是final字段，则只能在构 造函数中初始化，否则抛出IllegalAccessError。
        if (field.classMember.IsFinal()) {
            if (currentClass != field.classMember.Class() || !currentMethod.classMember.Name().equals("<init>")) {
                ExceptionUtils.throwException("java.lang.IllegalAccessError");
            }
        }
        String descriptor = field.classMember.Descriptor();
        int slotId = field.SlotId();
        OperandStack stack = frame.OperandStack();
        char[] c = descriptor.toCharArray();
        switch (c[0]) {
            case 'Z':
            case 'B':
            case 'C':
            case 'S':
            case 'I':
                int val = stack.PopInt();
                JObject ref = stack.PopRef();
                //先根据字段类型从操作数栈中弹出相应的变量值，然后弹出 对象引用。如果引用是null，需要抛出著名的空指针异常
                //(NullPointerException)，否则通过引用给实例变量赋值。
                if (ref == null) {
                    ExceptionUtils.throwException("java.lang.NullPointerException");
                }
                ref.Fields().SetInt(slotId, val);
                break;
            case 'F':
                float valf = stack.PopFloat();
                JObject reff = stack.PopRef();
                if (reff == null) {
                    ExceptionUtils.throwException("java.lang.NullPointerException");
                }
                reff.Fields().SetFloat(slotId, valf);
                break;
            case 'J':
                long vall = stack.PopLong();
                JObject refj = stack.PopRef();
                if (refj == null) {
                    ExceptionUtils.throwException("java.lang.NullPointerException");
                }
                refj.Fields().SetLong(slotId, vall);
                break;
            case 'D':
                double vald = stack.PopDouble();
                JObject refd = stack.PopRef();
                if (refd == null) {
                    ExceptionUtils.throwException("java.lang.NullPointerException");
                }
                refd.Fields().SetDouble(slotId, vald);
                break;
            case 'L':
            case '[':
                JObject valx = stack.PopRef();
                JObject refx = stack.PopRef();
                if (refx == null) {
                    ExceptionUtils.throwException("java.lang.NullPointerException");
                }
                refx.Fields().SetRef(slotId, valx);
                break;
            default:

        }

    }
}
