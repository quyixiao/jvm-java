package com.jvm.instructions.stores.lstore;

import com.jvm.instructions.base.NoOperandsInstruction;
import com.jvm.rtda.Frame;

public class LSTORE_0 extends NoOperandsInstruction {
    @Override
    public void Execute(Frame frame) {
        _lstore(frame, 0);
    }
}
