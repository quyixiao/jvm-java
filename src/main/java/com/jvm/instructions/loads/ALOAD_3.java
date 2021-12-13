package com.jvm.instructions.loads;

import com.jvm.instructions.base.NoOperandsInstruction;
import com.jvm.rtda.Frame;

public class ALOAD_3 extends NoOperandsInstruction {
    @Override
    public void Execute(Frame frame) {
        _aload(frame, 3);
    }
}
