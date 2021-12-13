package com.jvm.instructions.extended;


import com.jvm.instructions.base.Base;
import com.jvm.instructions.base.BytecodeReader;
import com.jvm.instructions.base.Instruction;
import com.jvm.rtda.Frame;


// Branch always (wide index)
public class GOTO_W implements Instruction {
    public int offset;

    //goto_w指令和goto指令的唯一区别就是索引从2字节变成了4 字节。
    @Override
    public void FetchOperands(BytecodeReader reader) {
        this.offset = reader.ReadInt32();
    }

    @Override
    public void Execute(Frame frame) {
        Base.Branch(frame, this.offset);
    }
}
