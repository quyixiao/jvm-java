package com.jvm.instructions.stores.lstore;

import com.jvm.instructions.base.Index8Instruction;
import com.jvm.rtda.Frame;
// Store long into local variable
public class LSTORE extends Index8Instruction {
    @Override
    public void Execute(Frame frame) {
        _lstore(frame, this.Index);
    }
}
