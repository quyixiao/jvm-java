package com.jvm.instructions.control.returnx;


import com.jvm.instructions.base.NoOperandsInstruction;
import com.jvm.rtda.Frame;
import com.jvm.rtda.JThread;

// Return double from method
public class DRETURN  extends NoOperandsInstruction {
    @Override
    public void Execute(Frame frame) {
        JThread thread = frame.Thread();
        Frame currentFrame = thread.PopFrame();
        Frame invokerFrame = thread.TopFrame();
        double val = currentFrame.OperandStack().PopDouble();
        invokerFrame.OperandStack().PushDouble(val);
    }
}
