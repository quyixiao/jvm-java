package com.jvm.instructions.base;

import com.jvm.rtda.Frame;

public abstract class Index8Instruction implements Instruction {

    public int Index;

    @Override
    public void FetchOperands(BytecodeReader reader) {
        this.Index = reader.ReadUint8().Value();
    }


    public void _aload(Frame frame, int index) {
        Object ref = frame.LocalVars().GetRef(index);
        frame.OperandStack().PushRef(ref);
    }


    public void _dload(Frame frame, int index) {
        double val = frame.LocalVars().GetDouble(index);
        frame.OperandStack().PushDouble(val);
    }


    public void _iload(Frame frame, int index) {
        int val = frame.LocalVars().GetInt(index);
        frame.OperandStack().PushInt(val);
    }


    public void _lload(Frame frame, int index) {
        Long val = frame.LocalVars().GetLong(index);
        frame.OperandStack().PushLong(val);
    }


    public void _fload(Frame frame, int index) {
        float val = frame.LocalVars().GetFloat(index);
        frame.OperandStack().PushFloat(val);
    }


}
