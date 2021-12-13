package com.jvm.instructions.loads.iload;

import com.jvm.instructions.base.NoOperandsInstruction;
import com.jvm.rtda.Frame;

public class ILOAD_3 extends NoOperandsInstruction {
    @Override
    public void Execute(Frame frame) {
        _iload(frame, 3);
    }
}
