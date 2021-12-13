package com.jvm.instructions.stack.dup;


import com.jvm.instructions.base.NoOperandsInstruction;
import com.jvm.rtda.Frame;
import com.jvm.rtda.OperandStack;
import com.jvm.rtda.Slot;

// Duplicate the top one or two operand stack values
public class DUP2 extends NoOperandsInstruction {
    /*
    bottom -> top
    [...][c][b][a]____
              \____   |
                   |  |
                   V  V
    [...][c][b][a][b][a]
    */
    @Override
    public void Execute(Frame frame) {
        OperandStack stack = frame.OperandStack();
        Slot slot1 = stack.PopSlot();
        Slot slot2 = stack.PopSlot();
        stack.PushSlot(slot2);
        stack.PushSlot(slot1);
        stack.PushSlot(slot2);
        stack.PushSlot(slot1);
    }
}
