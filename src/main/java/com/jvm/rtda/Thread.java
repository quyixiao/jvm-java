package com.jvm.rtda;


/*
JVM
  Thread
    pc
    Stack
      Frame
        LocalVars
        OperandStack
*/
public class Thread {

    public int pc;     // the address of the instruction currently being executed
    public Stack stack;


    public Thread() {
        this.stack = new Stack(1024);
    }


    public Integer PC() {
        return this.pc;
    }


    public void SetPC(Integer pc) {
        this.pc = pc;
    }

    //PushFrame()和PopFrame()方法只是调用Stack结构体的相应 方法而已
    public void PushFrame(Frame frame) {
        this.stack.push(frame);
    }

    public Frame PopFrame() {
        return this.stack.pop();
    }

    //CurrentFrame()方法返回当前帧
    public Frame CurrentFrame() {
        return this.stack.top();
    }


    public Frame NewFrame(int maxLocals, int maxStack) {
        return newFrame(this, maxLocals, maxStack);
    }


    //Thread结构体的NewFrame()方法是新增加的
    public Frame newFrame(Thread thread, int maxLocals, int maxStack) {
        return new Frame(
                thread,
                new LocalVars(maxLocals),
                new OperandStack(maxStack)
        );
    }


}
