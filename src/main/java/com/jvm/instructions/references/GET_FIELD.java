package com.jvm.instructions.references;

import com.jvm.instructions.base.Index16Instruction;
import com.jvm.rtda.Frame;
import com.jvm.rtda.OperandStack;
import com.jvm.rtda.heap.*;
import com.jvm.utils.ExceptionUtils;

public class GET_FIELD extends Index16Instruction {
    @Override
    public void Execute(Frame frame) {
        JConstantPool cp = frame.Method().classMember.Class().ConstantPool();
        FieldRef fieldRef = (FieldRef) cp.GetConstant(this.Index);
        JField field = fieldRef.ResolvedField();

        if (field.classMember.IsStatic()) {
            ExceptionUtils.throwException("java.lang.IncompatibleClassChangeError");
        }
        OperandStack stack = frame.OperandStack();
        JObject ref = stack.PopRef();
        if (ref == null) {
            ExceptionUtils.throwException("java.lang.NullPointerException");
        }

        //弹出对象引用，如果是null，则抛出NullPointerException
        String descriptor = field.classMember.Descriptor();
        int slotId = field.SlotId();
        Slots slots = ref.Fields();

        char[] c = descriptor.toCharArray();
        //根据字段类型，获取相应的实例变量值，然后推入操作数栈。 至此，getfield指令也解释完毕了
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

        }
    }
}
