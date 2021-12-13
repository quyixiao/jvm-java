package com.jvm.instructions.base;

import com.jvm.rtda.Frame;

//有一些指令需要访问运行时常量池，常量池索引由两字节操 作数给出。把这类指令抽象成Index16Instruction结构体，
//用Index字 段表示常量池索引。FetchOperands()方法从字节码中读取一个 uint16整数，转成uint后赋给Index字段
public abstract class Index16Instruction implements Instruction {

    public int Index;

    @Override
    public void FetchOperands(BytecodeReader reader) {
        this.Index = reader.ReadUint16().Value();
    }


}
