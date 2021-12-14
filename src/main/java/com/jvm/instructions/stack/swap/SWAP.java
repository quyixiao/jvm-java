package com.jvm.instructions.stack.swap;

import com.jvm.instructions.base.NoOperandsInstruction;
import com.jvm.rtda.Frame;
import com.jvm.rtda.OperandStack;
import com.jvm.rtda.Slot;

// Swap the top two operand stack values
public class SWAP extends NoOperandsInstruction {



    /*
    bottom -> top
    [...][c][b][a]
              \/
              /\
             V  V
    [...][c][a][b]
    */

    @Override
    public void Execute(Frame frame) {
        OperandStack stack = frame.OperandStack();
        Slot slot1 = stack.PopSlot();
        Slot slot2 = stack.PopSlot();
        stack.PushSlot(slot1);
        stack.PushSlot(slot2);
    }


}
