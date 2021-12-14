package com.jvm.instructions.base;


import com.jvm.rtda.Frame;
import com.jvm.rtda.OperandStack;

//NoOperandsInstruction表示没有操作数的指令，所以没有定义
public abstract class NoOperandsInstruction extends AbstractInstruction implements Instruction {
    @Override
    public void FetchOperands(BytecodeReader reader) {
        // empty

    }


    public void _dcmp(Frame frame, boolean gFlag) {
        OperandStack stack = frame.OperandStack();
        double v2 = stack.PopDouble();
        double v1 = stack.PopDouble();
        if (v1 > v2) {
            stack.PushInt(1);
        } else if (v1 == v2) {
            stack.PushInt(0);
        } else if (v1 < v2) {
            stack.PushInt(-1);
        } else if (gFlag) {
            stack.PushInt(1);
        } else {
            stack.PushInt(-1);
        }
    }


    public void _fcmp(Frame frame, boolean gFlag) {
        OperandStack stack = frame.OperandStack();
        float v2 = stack.PopFloat();
        float v1 = stack.PopFloat();
        if (v1 > v2) {
            stack.PushInt(1);
        } else if (v1 == v2) {
            stack.PushInt(0);
        } else if (v1 < v2) {
            stack.PushInt(-1);
        } else if (gFlag) { //也就是说，当两个float变量中至少有一个是NaN时，用fcmpg指 令比较的结果是1，而用fcmpl指令比较的结果是-1。
            stack.PushInt(1);
        } else {
            stack.PushInt(-1);
        }
    }



}



