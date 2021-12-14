package com.jvm.instructions.stores.dstore;

import com.jvm.instructions.base.NoOperandsInstruction;
import com.jvm.rtda.Frame;

public class DSTORE_0 extends NoOperandsInstruction {
    @Override
    public void Execute(Frame frame) {
        _dstore(frame, 0);
    }
}
