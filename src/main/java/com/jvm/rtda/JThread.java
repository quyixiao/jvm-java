package com.jvm.rtda;


import com.jvm.rtda.heap.JMethod;

/*
JVM
  Thread
    pc
    Stack
      Frame
        LocalVars
        OperandStack
*/
public class JThread {

    public int pc;     // the address of the instruction currently being executed
    public Stack stack;


    public JThread() {
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
    public Frame newFrame(JThread thread, int maxLocals, int maxStack) {
        return new Frame(
                thread,
                new LocalVars(maxLocals),
                new OperandStack(maxStack)
        );
    }


    public Frame  newFrame(JThread thread, JMethod method)  {
        return new Frame(
                  thread,  method,
                    new LocalVars(method.MaxLocals()),
                    new OperandStack(method.MaxStack())
        ) ;
    }



    public Frame NewFrame(JMethod method)  {
        return newFrame(this, method);
    }






    public Frame TopFrame() {
        return this.stack.top();
    }

    public boolean IsStackEmpty()  {
        return this.stack.isEmpty();
    }


}
