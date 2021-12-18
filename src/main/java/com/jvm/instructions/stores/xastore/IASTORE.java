package com.jvm.instructions.stores.xastore;

import com.jvm.instructions.base.NoOperandsInstruction;
import com.jvm.rtda.Frame;
import com.jvm.rtda.OperandStack;
import com.jvm.rtda.heap.JObject;


// Store into int array
public class IASTORE extends NoOperandsInstruction {
    @Override
    public void Execute(Frame frame) {
        OperandStack stack = frame.OperandStack();
        int val = stack.PopInt();
        int index = stack.PopInt();
        JObject arrRef = stack.PopRef();

        checkNotNil(arrRef);
        int ints[] = arrRef.Ints();
        checkIndex(ints.length, index);
        ints[index] = val;
    }
}
