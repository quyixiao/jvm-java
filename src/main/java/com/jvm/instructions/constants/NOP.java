package com.jvm.instructions.constants;

import com.jvm.instructions.base.NoOperandsInstruction;
import com.jvm.rtda.Frame;

// Do nothing
public class NOP  extends NoOperandsInstruction {
    @Override
    public void Execute(Frame frame) {
        // really do nothing
    }
}
