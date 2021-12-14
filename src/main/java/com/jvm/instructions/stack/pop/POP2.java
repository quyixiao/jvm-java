package com.jvm.instructions.stack.pop;


import com.jvm.instructions.base.NoOperandsInstruction;
import com.jvm.rtda.Frame;
import com.jvm.rtda.OperandStack;

// Pop the top one or two operand stack values
public class POP2  extends NoOperandsInstruction {


    /*
    bottom -> top
    [...][c][b][a]
             |  |
             V  V
    [...][c]
    pop指令只能用于弹出int、float等占用一个操作数栈位置的变 量。double和long变量在操作数栈中占据两个位置，需要使用pop2 指令弹出
    */
    @Override
    public void Execute(Frame frame) {
        OperandStack stack = frame.OperandStack();
        stack.PopSlot();
        stack.PopSlot();
    }
}
