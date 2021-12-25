package com.jvm.instructions.base;

import com.jvm.rtda.Frame;
import com.jvm.rtda.OperandStack;
import com.jvm.utils.t.Tuple2;

//BranchInstruction表示跳转指令，Offset字段存放跳转偏移量。
public abstract class BranchInstruction implements Instruction {

    public int Offset;

    @Override
    public void FetchOperands(BytecodeReader reader) {
        this.Offset = reader.ReadInt16();
    }


    public boolean _acmp(Frame frame) {
        OperandStack stack = frame.OperandStack();
        Object ref2 = stack.PopRef();
        Object ref1 = stack.PopRef();
        return ref1 == ref2;
    }

    public Tuple2<Integer ,Integer> _icmpPop(Frame frame) {
        OperandStack stack = frame.OperandStack();
        Integer val2 = stack.PopInt();
        Integer val1 = stack.PopInt();
        return new Tuple2<>(val1,val2);
    }

    @Override
    public String toString() {
        return this.getClass().getSimpleName() + " {" +
                "Offset=" + Offset +
                '}';
    }
}
