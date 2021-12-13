package com.jvm.instructions.loads.dload;

import com.jvm.instructions.base.NoOperandsInstruction;
import com.jvm.rtda.Frame;

public class DLOAD_1 extends NoOperandsInstruction {
    @Override
    public void Execute(Frame frame) {
        _dload(frame, 1);
    }
}
