package com.jvm.instructions.loads.fload;

import com.jvm.instructions.base.NoOperandsInstruction;
import com.jvm.rtda.Frame;

public class FLOAD_2 extends NoOperandsInstruction {
    @Override
    public void Execute(Frame frame) {
        _fload(frame, 2);
    }
}
