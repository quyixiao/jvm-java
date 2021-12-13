package com.jvm.instructions.comparisons.fcmp;

import com.jvm.instructions.base.NoOperandsInstruction;
import com.jvm.rtda.Frame;

public class FCMPG extends NoOperandsInstruction {

    @Override
    public void Execute(Frame frame) {
        _fcmp(frame, true);
    }
}
