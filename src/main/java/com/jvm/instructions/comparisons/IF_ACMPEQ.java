package com.jvm.instructions.comparisons;

import com.jvm.instructions.base.Base;
import com.jvm.instructions.base.BranchInstruction;
import com.jvm.rtda.Frame;

public class IF_ACMPEQ extends BranchInstruction {


    @Override
    public void Execute(Frame frame) {
        if (_acmp(frame)){
            Base.Branch(frame, this.Offset);
        }
    }
}
