package com.jvm.instructions.comparisons.fcmp;

import com.jvm.instructions.base.BytecodeReader;
import com.jvm.instructions.base.Instruction;
import com.jvm.instructions.base.NoOperandsInstruction;
import com.jvm.rtda.Frame;

public class FCMPL  extends NoOperandsInstruction {

    @Override
    public void Execute(Frame frame) {
        _fcmp(frame, false);
    }
}
