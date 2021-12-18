package com.jvm.instructions.stores.xastore;

import com.jvm.instructions.base.NoOperandsInstruction;
import com.jvm.rtda.Frame;
import com.jvm.rtda.OperandStack;
import com.jvm.rtda.heap.JObject;

// Store into char array
public class CASTORE extends NoOperandsInstruction {
    @Override
    public void Execute(Frame frame) {
        OperandStack stack = frame.OperandStack();
        int val = stack.PopInt();
        int index = stack.PopInt();
        JObject arrRef = stack.PopRef();

        checkNotNil(arrRef);
        char chars[] = arrRef.Chars();
        checkIndex(chars.length, index);
        chars[index] = (char) val;
    }
}
