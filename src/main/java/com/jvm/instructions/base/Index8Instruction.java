package com.jvm.instructions.base;

import com.jvm.rtda.Frame;

public abstract class Index8Instruction extends AbstractInstruction implements Instruction {

    public int Index;

    @Override
    public void FetchOperands(BytecodeReader reader) {
        this.Index = reader.ReadUint8().Value();
    }

}
