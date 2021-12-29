package com.jvm.instructions.base;

public abstract class Index8Instruction extends AbstractInstruction implements Instruction {

    public int Index;

    @Override
    public void FetchOperands(BytecodeReader reader) {
        this.Index = reader.ReadUint8().Value();
    }

    @Override
    public String toString() {
        return this.getClass().getSimpleName() + " {" +
                "Index=" + Index +
                '}';
    }
}
