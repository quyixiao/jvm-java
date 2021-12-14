package com.jvm.instructions.stores.astore;

import com.jvm.instructions.base.NoOperandsInstruction;
import com.jvm.rtda.Frame;

public class ASTORE_0 extends NoOperandsInstruction {
    @Override
    public void Execute(Frame frame) {
        _astore(frame, 0);
    }
}
