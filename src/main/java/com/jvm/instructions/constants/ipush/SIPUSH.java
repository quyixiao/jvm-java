package com.jvm.instructions.constants.ipush;

import com.jvm.instructions.base.BytecodeReader;
import com.jvm.instructions.base.Instruction;
import com.jvm.rtda.Frame;


// Push short
public class SIPUSH implements Instruction {

    public Integer val;

    @Override
    public void FetchOperands(BytecodeReader reader) {
        this.val = reader.ReadInt16();
    }

    @Override
    public void Execute(Frame frame) {
        frame.OperandStack().PushInt(this.val);
    }
}
