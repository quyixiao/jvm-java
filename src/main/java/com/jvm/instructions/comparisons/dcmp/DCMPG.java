package com.jvm.instructions.comparisons.dcmp;

import com.jvm.instructions.base.NoOperandsInstruction;
import com.jvm.rtda.Frame;

public class DCMPG extends NoOperandsInstruction {
    @Override
    public void Execute(Frame frame) {
        _dcmp(frame,true);
    }
}
