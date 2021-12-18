package com.jvm.instructions.stores.xastore;

import com.jvm.instructions.base.NoOperandsInstruction;
import com.jvm.rtda.Frame;
import com.jvm.rtda.OperandStack;
import com.jvm.rtda.heap.JObject;


// Store into long array
public class LASTORE extends NoOperandsInstruction {
    @Override
    public void Execute(Frame frame) {
        OperandStack stack = frame.OperandStack();
        long val = stack.PopLong();
        int index = stack.PopInt();
        JObject arrRef = stack.PopRef();

        checkNotNil(arrRef);
        long longs[] = arrRef.Longs();
        checkIndex(longs.length, index);
        longs[index] = val;
    }
}
