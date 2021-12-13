package com.jvm.instructions.stack.dup;

import com.jvm.instructions.base.NoOperandsInstruction;
import com.jvm.rtda.Frame;
import com.jvm.rtda.OperandStack;
import com.jvm.rtda.Slot;

// Duplicate the top operand stack value
public class DUP extends NoOperandsInstruction {
    /*
    bottom -> top
    [...][c][b][a]
                 \_
                   |
                   V
    [...][c][b][a][a]
    */
    @Override
    public void Execute(Frame frame) {
        OperandStack stack = frame.OperandStack();
        Slot slot = stack.PopSlot();
        stack.PushSlot(slot);
        stack.PushSlot(slot);
    }
}
