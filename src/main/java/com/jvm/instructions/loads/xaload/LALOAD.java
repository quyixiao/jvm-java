package com.jvm.instructions.loads.xaload;

import com.jvm.instructions.base.NoOperandsInstruction;
import com.jvm.rtda.Frame;
import com.jvm.rtda.OperandStack;
import com.jvm.rtda.heap.JObject;

// Load long from array
public class LALOAD extends NoOperandsInstruction {
    @Override
    public void Execute(Frame frame) {
        OperandStack stack = frame.OperandStack();
        int index = stack.PopInt();
        JObject arrRef = stack.PopRef();

        checkNotNil(arrRef);
        long longs[] = arrRef.Longs();
        checkIndex(longs.length, index);
        stack.PushLong(longs[index]);
    }
}
