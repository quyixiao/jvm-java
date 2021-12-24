package com.jvm.instructions.stack.dup;

import com.jvm.instructions.base.NoOperandsInstruction;
import com.jvm.rtda.Frame;
import com.jvm.rtda.OperandStack;
import com.jvm.rtda.Slot;

// Duplicate the top one or two operand stack values and insert two or three values down
public class DUP2_X1 extends NoOperandsInstruction {


    /*
    bottom -> top
    [...][c][b][a]
           _/ __/
          |  |
          V  V
    [...][b][a][c][b][a]
    */
    @Override
    public void Execute(Frame frame) {
        OperandStack stack = frame.OperandStack();
        Slot slot1 = stack.PopSlot();
        Slot slot2 = stack.PopSlot();
        Slot slot3 = stack.PopSlot();

        Slot slot11 = slot1.clone();
        Slot slot22 = slot2.clone();


        stack.PushSlot(slot22);
        stack.PushSlot(slot11);
        stack.PushSlot(slot3);
        stack.PushSlot(slot2);
        stack.PushSlot(slot1);
    }
}
