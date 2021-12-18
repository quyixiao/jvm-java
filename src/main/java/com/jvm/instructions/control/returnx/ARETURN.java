package com.jvm.instructions.control.returnx;

import com.jvm.instructions.base.NoOperandsInstruction;
import com.jvm.rtda.Frame;
import com.jvm.rtda.JThread;
import com.jvm.rtda.heap.JObject;

// Return reference from method
public class ARETURN extends NoOperandsInstruction {
    @Override
    public void Execute(Frame frame) {
        JThread thread = frame.Thread();
        Frame currentFrame= thread.PopFrame();
        Frame invokerFrame = thread.TopFrame();
        JObject ref = currentFrame.OperandStack().PopRef();
        invokerFrame.OperandStack().PushRef(ref);
    }
}
