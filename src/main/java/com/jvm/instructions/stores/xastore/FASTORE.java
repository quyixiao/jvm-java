package com.jvm.instructions.stores.xastore;

import com.jvm.instructions.base.NoOperandsInstruction;
import com.jvm.rtda.Frame;
import com.jvm.rtda.OperandStack;
import com.jvm.rtda.heap.JObject;
// Store into float array
public class FASTORE extends NoOperandsInstruction {
    @Override
    public void Execute(Frame frame) {
        OperandStack stack = frame.OperandStack();
        float val = stack.PopFloat();
        int index = stack.PopInt();
        JObject arrRef = stack.PopRef();

        checkNotNil(arrRef);
        float floats[] = arrRef.Floats();
        checkIndex(floats.length, index);
        floats[index] = val;
    }
}
