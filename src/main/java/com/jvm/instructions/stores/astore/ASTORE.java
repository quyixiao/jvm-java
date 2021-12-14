package com.jvm.instructions.stores.astore;

import com.jvm.instructions.base.Index8Instruction;
import com.jvm.rtda.Frame;

// Store reference into local variable
public class ASTORE extends Index8Instruction {
    @Override
    public void Execute(Frame frame) {
        _astore(frame, this.Index);
    }
}
