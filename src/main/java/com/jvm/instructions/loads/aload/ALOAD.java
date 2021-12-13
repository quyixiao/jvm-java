package com.jvm.instructions.loads.aload;

import com.jvm.instructions.base.Index8Instruction;
import com.jvm.rtda.Frame;

// Load reference from local variable
//aload系列指令 操作引用类型变量
public class ALOAD extends Index8Instruction {
    @Override
    public void Execute(Frame frame) {
        _aload(frame, this.Index);
    }
}
