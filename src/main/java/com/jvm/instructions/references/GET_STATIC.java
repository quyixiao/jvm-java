package com.jvm.instructions.references;


import com.jvm.instructions.base.Index16Instruction;
import com.jvm.rtda.Frame;
import com.jvm.rtda.OperandStack;
import com.jvm.rtda.heap.*;
import com.jvm.utils.ExceptionUtils;

// Get static field from class
public class GET_STATIC extends Index16Instruction {
    @Override
    public void Execute(Frame frame) {
        JConstantPool cp = frame.Method().classMember.Class().ConstantPool();
        FieldRef fieldRef = (FieldRef) cp.GetConstant(this.Index);
        JField field = fieldRef.ResolvedField();
        JClass jClass = field.classMember.Class();
        // todo: init class
        if (!field.classMember.IsStatic()) {
            //如果解析后的字段不是静态字段，也要抛出 IncompatibleClassChangeError异常
            ExceptionUtils.throwException("java.lang.IncompatibleClassChangeError");
        }
        String descriptor = field.classMember.Descriptor();
        int slotId = field.SlotId();
        Slots slots = jClass.StaticVars();
        OperandStack stack = frame.OperandStack();
        char[] c = descriptor.toCharArray();
        switch (c[0]) {
            case 'Z':
            case 'B':
            case 'C':
            case 'S':
            case 'I':
                stack.PushInt(slots.GetInt(slotId));
                break;
            case 'F':
                stack.PushFloat(slots.GetFloat(slotId));
                break;
            case 'J':
                stack.PushLong(slots.GetLong(slotId));
                break;
            case 'D':
                stack.PushDouble(slots.GetDouble(slotId));
                break;
            case 'L':
            case '[':
                stack.PushRef(slots.GetRef(slotId));
                break;
            default:
                // todo
        }
    }
}
