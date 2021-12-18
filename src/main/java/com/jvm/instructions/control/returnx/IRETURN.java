package com.jvm.instructions.control.returnx;

import com.jvm.instructions.base.NoOperandsInstruction;
import com.jvm.rtda.Frame;
import com.jvm.rtda.JThread;

// Return int from method
public class IRETURN extends NoOperandsInstruction {
    @Override
    public void Execute(Frame frame) {
        JThread thread = frame.Thread();
        Frame currentFrame = thread.PopFrame();
        Frame invokerFrame = thread.TopFrame();
        int val = currentFrame.OperandStack().PopInt();
        invokerFrame.OperandStack().PushInt(val);
    }
}
