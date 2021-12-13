package com.jvm.instructions.loads;

import com.jvm.instructions.base.NoOperandsInstruction;
import com.jvm.rtda.Frame;

public class DLOAD_3 extends NoOperandsInstruction {
    @Override
    public void Execute(Frame frame) {
        _dload(frame, 3);
    }
}
