package com.jvm.instructions.references.array;

import com.jvm.instructions.base.NoOperandsInstruction;
import com.jvm.rtda.Frame;
import com.jvm.rtda.OperandStack;
import com.jvm.rtda.heap.JObject;
import com.jvm.utils.ExceptionUtils;

// Get length of array
public class ARRAY_LENGTH extends NoOperandsInstruction {
    @Override
    public void Execute(Frame frame) {
        OperandStack stack = frame.OperandStack();
        //如果数组引用是null，则需要抛出NullPointerException异常，否 则取数组长度，推入操作数栈顶即可
        JObject arrRef = stack.PopRef();
        if (arrRef == null) {
            ExceptionUtils.throwException("java.lang.NullPointerException");
        }

        int arrLen = arrRef.ArrayLength();
        stack.PushInt(arrLen);
    }
}
