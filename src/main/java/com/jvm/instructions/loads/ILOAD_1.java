package com.jvm.instructions.loads;

import com.jvm.instructions.base.NoOperandsInstruction;
import com.jvm.rtda.Frame;

public class ILOAD_1 extends NoOperandsInstruction {
    @Override
    public void Execute(Frame frame) {
        _iload(frame, 1);
    }
}