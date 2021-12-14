package com.jvm.instructions.base;

import com.jvm.rtda.Frame;

public abstract class AbstractInstruction {


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


    public void _astore(Frame frame, int index) {
        Object ref = frame.OperandStack().PopRef();
        frame.LocalVars().SetRef(index, ref);
    }


    public void _dstore(Frame frame, int index) {
        double val = frame.OperandStack().PopDouble();
        frame.LocalVars().SetDouble(index, val);
    }


    public void _fstore(Frame frame, int index) {
        float val = frame.OperandStack().PopFloat();
        frame.LocalVars().SetFloat(index, val);
    }


    public void _istore(Frame frame, int index) {
        int val = frame.OperandStack().PopInt();
        frame.LocalVars().SetInt(index, val);
    }


    public void _lstore(Frame frame, int index) {
        long val = frame.OperandStack().PopLong();
        frame.LocalVars().SetLong(index, val);
    }

}
