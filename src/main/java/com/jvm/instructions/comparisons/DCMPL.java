package com.jvm.instructions.comparisons;

import com.jvm.instructions.base.NoOperandsInstruction;
import com.jvm.rtda.Frame;

public class DCMPL extends NoOperandsInstruction {
    @Override
    public void Execute(Frame frame) {
        _dcmp(frame,false);
    }
}
