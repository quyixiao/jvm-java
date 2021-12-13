package com.jvm.instructions.extended.ifnull;

import com.jvm.instructions.base.Base;
import com.jvm.instructions.base.BranchInstruction;
import com.jvm.rtda.Frame;

public class IFNONNULL extends BranchInstruction {
    @Override
    public void Execute(Frame frame) {
        Object ref = frame.OperandStack().PopRef();
        if (ref != null) {
            Base.Branch(frame, this.Offset);
        }
    }
}
