package com.jvm.instructions.stores.istore;

import com.jvm.instructions.base.Index8Instruction;
import com.jvm.rtda.Frame;

public class ISTORE extends Index8Instruction {
    @Override
    public void Execute(Frame frame) {
        _istore(frame, this.Index);
    }
}
