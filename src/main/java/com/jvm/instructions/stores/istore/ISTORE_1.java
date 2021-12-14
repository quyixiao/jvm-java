package com.jvm.instructions.stores.istore;

import com.jvm.instructions.base.NoOperandsInstruction;
import com.jvm.rtda.Frame;

public class ISTORE_1 extends NoOperandsInstruction {
    @Override
    public void Execute(Frame frame) {
        _istore(frame, 1);
    }
}
