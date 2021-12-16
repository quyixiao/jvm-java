package com.jvm.instructions.control.returnx;

import com.jvm.instructions.base.NoOperandsInstruction;
import com.jvm.rtda.Frame;

public class RETURN extends NoOperandsInstruction {
    @Override
    public void Execute(Frame frame) {
        frame.Thread().PopFrame();
    }
}
