package com.jvm.instructions.control.returnx;


import com.jvm.instructions.base.NoOperandsInstruction;
import com.jvm.rtda.Frame;
import com.jvm.rtda.JThread;

// Return float from method
public class FRETURN extends NoOperandsInstruction {
    @Override
    public void Execute(Frame frame) {
        JThread thread = frame.Thread();
        Frame currentFrame = thread.PopFrame();
        Frame invokerFrame = thread.TopFrame();
        float val = currentFrame.OperandStack().PopFloat();
        invokerFrame.OperandStack().PushFloat(val);
    }
}
