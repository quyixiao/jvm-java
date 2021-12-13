package com.jvm.instructions.loads.aload;

import com.jvm.instructions.base.NoOperandsInstruction;
import com.jvm.rtda.Frame;

public class ALOAD_1 extends NoOperandsInstruction {
    @Override
    public void Execute(Frame frame) {
        _aload(frame, 1);
    }
}
