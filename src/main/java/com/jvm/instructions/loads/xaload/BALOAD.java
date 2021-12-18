package com.jvm.instructions.loads.xaload;

import com.jvm.instructions.base.NoOperandsInstruction;
import com.jvm.rtda.Frame;
import com.jvm.rtda.OperandStack;
import com.jvm.rtda.heap.JObject;

// Load byte or boolean from array
public class BALOAD extends NoOperandsInstruction {
    @Override
    public void Execute(Frame frame) {
        OperandStack stack = frame.OperandStack();
        int index = stack.PopInt();
        JObject arrRef = stack.PopRef();

        checkNotNil(arrRef);
        byte bytes[] = arrRef.Bytes();
        checkIndex(bytes.length, index);
        stack.PushInt((int) bytes[index]);
    }
}
