package com.jvm.instructions.loads;

import com.jvm.instructions.base.Index8Instruction;
import com.jvm.rtda.Frame;

public class LLOAD extends Index8Instruction {
    @Override
    public void Execute(Frame frame) {
        _lload(frame, this.Index);
    }
}
