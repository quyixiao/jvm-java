package com.jvm.rtda;

import com.jvm.utils.ExceptionUtils;
import lombok.Data;

@Data
public class Stack {

    public Integer maxSize;                        //maxSize字段保存栈的容量(最多可以容纳多少帧)
    public Integer size;            //size字段保 存栈的当前大小，
    public Frame _top; // stack is implemented as linked list _top字段保存栈顶指针


    //经典的链表(linked list)数据结构来实现Java虚拟机栈，这样 栈就可以按需使用内存空间，而且弹出的帧也可以及时被Go的垃 圾收集器回收
    public Stack(Integer maxSize) {
        this.maxSize = maxSize;
    }


    //push()方法把帧推入栈顶
    public void push(Frame frame) {
        //如果栈已经满了，按照Java虚拟机规范，应该抛出 StackOverflowError异常。在第10章才会讨论异常，
        //这里先调用 panic()函数终止程序执行
        if (this.size >= this.maxSize) {
            ExceptionUtils.throwException("java.lang.StackOverflowError");
        }

        if (this._top != null) {
            frame.lower = this._top;
        }

        this._top = frame;
        this.size++;
    }



    //如果此时栈是空的，肯定是我们的虚拟机有bug，调用panic() 函数终止程序执行即可
//top()方法只是返回栈顶帧，但并不弹出
    public Frame pop()  {
        if (this._top == null) {
            ExceptionUtils.throwException(" jvm stack is empty!");
        }

        Frame top= this._top;
        this._top = top.lower;
        top.lower = null;
        this.size--;

        return top;
    }

    public Frame top() {
        if (this._top == null) {
            ExceptionUtils.throwException("jvm stack is empty!");
        }
        return this._top;
    }


}
