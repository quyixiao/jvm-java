package com.jvm.instructions.stores.xastore;


import com.jvm.instructions.base.NoOperandsInstruction;
import com.jvm.rtda.Frame;
import com.jvm.rtda.OperandStack;
import com.jvm.rtda.heap.JObject;

// Store into double array
public class DASTORE extends NoOperandsInstruction {
    @Override
    public void Execute(Frame frame) {
        OperandStack stack = frame.OperandStack();
        double val = stack.PopDouble();
        int index = stack.PopInt();
        JObject arrRef = stack.PopRef();

        checkNotNil(arrRef);
        double doubles[] = arrRef.Doubles();
        checkIndex(doubles.length, index);
        doubles[index] = val;
    }
}
