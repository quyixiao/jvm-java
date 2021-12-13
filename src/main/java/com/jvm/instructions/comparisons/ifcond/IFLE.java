package com.jvm.instructions.comparisons.ifcond;

import com.jvm.instructions.base.Base;
import com.jvm.instructions.base.BranchInstruction;
import com.jvm.rtda.Frame;

public class IFLE extends BranchInstruction {
    @Override
    public void Execute(Frame frame) {
       Integer  val = frame.OperandStack().PopInt();
        if (val <= 0) {
            Base.Branch(frame, this.Offset);
        }
    }
}
