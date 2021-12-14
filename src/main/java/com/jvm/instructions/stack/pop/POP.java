package com.jvm.instructions.stack.pop;

import com.jvm.instructions.base.NoOperandsInstruction;
import com.jvm.rtda.Frame;
import com.jvm.rtda.OperandStack;


// Pop the top operand stack value
public class POP extends NoOperandsInstruction {


    /*
    bottom -> top
    [...][c][b][a]
                |
                V
    [...][c][b]
    pop指令把栈顶变量弹出
    */
    @Override
    public void Execute(Frame frame) {
        OperandStack stack = frame.OperandStack();
        stack.PopSlot();
    }
}
