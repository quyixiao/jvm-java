package com.jvm.instructions.stores.fstore;

import com.jvm.instructions.base.Index8Instruction;
import com.jvm.rtda.Frame;

public class FSTORE extends Index8Instruction {
    @Override
    public void Execute(Frame frame) {
        _fstore(frame, this.Index);
    }
}
