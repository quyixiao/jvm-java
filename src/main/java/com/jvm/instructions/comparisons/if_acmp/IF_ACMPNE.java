package com.jvm.instructions.comparisons.if_acmp;

import com.jvm.instructions.base.Base;
import com.jvm.instructions.base.BranchInstruction;
import com.jvm.rtda.Frame;

public class IF_ACMPNE extends BranchInstruction {


    @Override
    public void Execute(Frame frame) {
        if (!_acmp(frame)) {
            Base.Branch(frame, this.Offset);
        }
    }
}
