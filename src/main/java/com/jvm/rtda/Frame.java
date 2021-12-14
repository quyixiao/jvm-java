package com.jvm.rtda;

import lombok.Data;

@Data
public class Frame {
    public Frame lower;        //lower字段用来实现链表数据结构
    public LocalVars localVars;     //localVars字段保存 局部变量表指针
    public OperandStack operandStack; //operandStack字段保存操作数栈指针。
    public Thread thread;
    public int nextPC; // the next instruction after the call


    public Frame() {
    }

    public Frame(Integer maxLocals, Integer maxStack) {
        this.localVars = new LocalVars(maxLocals);
        this.operandStack = new OperandStack(maxStack);
    }

    public Frame( Thread thread, LocalVars localVars, OperandStack operandStack) {
        this.localVars = localVars;
        this.operandStack = operandStack;
        this.thread = thread;
    }

    // getters
    public LocalVars LocalVars() {
        return this.localVars;
    }

    public OperandStack OperandStack() {

        return this.operandStack;
    }


    public Thread Thread() {
        return this.thread;
    }

    public int NextPC() {

        return this.nextPC;
    }

    public void SetNextPC(int nextPC) {
        this.nextPC = nextPC;
    }


}
