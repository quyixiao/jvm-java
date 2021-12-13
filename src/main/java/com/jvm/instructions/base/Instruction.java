package com.jvm.instructions.base;

import com.jvm.rtda.Frame;

public interface Instruction {

    void FetchOperands(BytecodeReader reader);        //FetchOperands()方法从字节码中提取操作数

    void Execute(Frame frame);                        //Execute()方法 执行指令逻辑
}
