package com.jvm.rtda;

import com.jvm.rtda.heap.JMethod;
import lombok.Data;

@Data
public class Frame {
    public Frame lower;        //lower字段用来实现链表数据结构
    public LocalVars localVars;     //localVars字段保存 局部变量表指针
    public OperandStack operandStack; //operandStack字段保存操作数栈指针。
    public JThread thread;
    public int nextPC; // the next instruction after the call
    public JMethod method;


    public Frame() {
    }

    public Frame(Integer maxLocals, Integer maxStack) {
        this.localVars = new LocalVars(maxLocals);
        this.operandStack = new OperandStack(maxStack);
    }

    public Frame(JThread thread, LocalVars localVars, OperandStack operandStack) {
        this.localVars = localVars;
        this.operandStack = operandStack;
        this.thread = thread;
    }


    public Frame(JThread thread, JMethod method, LocalVars localVars, OperandStack operandStack) {
        this.localVars = localVars;
        this.operandStack = operandStack;
        this.thread = thread;
        this.method = method;
    }


    // getters
    public LocalVars LocalVars() {
        return this.localVars;
    }

    public OperandStack OperandStack() {

        return this.operandStack;
    }


    public JThread Thread() {
        return this.thread;
    }

    public int NextPC() {
        return this.nextPC;
    }

    public void SetNextPC(int nextPC) {
        this.nextPC = nextPC;
    }

    public JMethod Method() {
        return this.method;
    }



    public void  RevertNextPC() {
        this.nextPC = this.thread.pc;
    }


}
