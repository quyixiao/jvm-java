package com.jvm.instructions.stores.xastore;

import com.jvm.instructions.base.NoOperandsInstruction;
import com.jvm.rtda.Frame;
import com.jvm.rtda.OperandStack;
import com.jvm.rtda.heap.JObject;
// Store into byte or boolean array
public class BASTORE extends NoOperandsInstruction {
    @Override
    public void Execute(Frame frame) {
        OperandStack stack = frame.OperandStack();
        int val = stack.PopInt();
        int index = stack.PopInt();
        JObject arrRef = stack.PopRef();

        checkNotNil(arrRef);
        byte bytes[] = arrRef.Bytes();
        checkIndex(bytes.length, index);
        bytes[index] = (byte) val;
    }
}
