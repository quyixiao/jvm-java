package com.jvm.instructions.loads.xaload;

import com.jvm.instructions.base.NoOperandsInstruction;
import com.jvm.rtda.Frame;
import com.jvm.rtda.OperandStack;
import com.jvm.rtda.heap.JObject;

// Load int from array
public class IALOAD extends NoOperandsInstruction {
    @Override
    public void Execute(Frame frame) {
        OperandStack stack = frame.OperandStack();
        int index = stack.PopInt();
        JObject arrRef = stack.PopRef();

        checkNotNil(arrRef);
        int ints[] = arrRef.Ints();
        checkIndex(ints.length, index);
        stack.PushInt(ints[index]);
    }
}
