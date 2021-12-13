package com.jvm.instructions.loads.lload;

import com.jvm.instructions.base.NoOperandsInstruction;
import com.jvm.rtda.Frame;

public class LLOAD_3  extends NoOperandsInstruction {
    @Override
    public void Execute(Frame frame) {
        _lload(frame, 3);
    }
}
