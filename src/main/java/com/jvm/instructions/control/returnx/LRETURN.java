package com.jvm.instructions.control.returnx;


import com.jvm.instructions.base.NoOperandsInstruction;
import com.jvm.rtda.Frame;
import com.jvm.rtda.JThread;

// Return double from method
public class LRETURN extends NoOperandsInstruction {
    @Override
    public void Execute(Frame frame) {
        JThread thread = frame.Thread();
        Frame currentFrame = thread.PopFrame();
        Frame invokerFrame = thread.TopFrame();
        long val = currentFrame.OperandStack().PopLong();
        invokerFrame.OperandStack().PushLong(val);
    }
}
