package com.jvm.instructions.stores.fstore;

import com.jvm.instructions.base.NoOperandsInstruction;
import com.jvm.rtda.Frame;

public class FSTORE_3 extends NoOperandsInstruction {
    @Override
    public void Execute(Frame frame) {
        _fstore(frame, 3);
    }
}
