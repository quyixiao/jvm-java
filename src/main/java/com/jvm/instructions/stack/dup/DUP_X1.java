package com.jvm.instructions.stack.dup;

import com.jvm.instructions.base.NoOperandsInstruction;
import com.jvm.rtda.Frame;
import com.jvm.rtda.OperandStack;
import com.jvm.rtda.Slot;

// Duplicate the top operand stack value and insert two values down
public class DUP_X1 extends NoOperandsInstruction {

    /*
    bottom -> top
    [...][c][b][a]
              __/
             |
             V
    [...][c][a][b][a]
    */

    @Override
    public void Execute(Frame frame) {
        OperandStack stack = frame.OperandStack();
        Slot slot1 = stack.PopSlot();
        Slot slot2 = stack.PopSlot();

        Slot slot11 = slot1.clone();

        stack.PushSlot(slot11);
        stack.PushSlot(slot2);
        stack.PushSlot(slot1);
    }
}
