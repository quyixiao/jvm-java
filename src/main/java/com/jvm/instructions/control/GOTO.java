package com.jvm.instructions.control;

import com.jvm.instructions.base.Base;
import com.jvm.instructions.base.BranchInstruction;
import com.jvm.rtda.Frame;
import lombok.Data;

@Data
public class GOTO extends BranchInstruction {
    @Override
    public void Execute(Frame frame) {
        Base.Branch(frame, this.Offset);
    }


    @Override
    public String toString() {
        return "GOTO {" +
                "Offset=" + Offset +
                '}';
    }
}
