package com.jvm.instructions.loads;

import com.jvm.instructions.base.NoOperandsInstruction;
import com.jvm.rtda.Frame;

public class LLOAD_1 extends NoOperandsInstruction {
    @Override
    public void Execute(Frame frame) {
        _lload(frame, 1);
    }
}
