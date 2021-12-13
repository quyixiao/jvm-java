package com.jvm.instructions.loads.lload;

import com.jvm.instructions.base.NoOperandsInstruction;
import com.jvm.rtda.Frame;

public class LLOAD_0 extends NoOperandsInstruction {
    @Override
    public void Execute(Frame frame) {
        _lload(frame, 0);
    }
}
