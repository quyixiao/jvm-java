package com.jvm.instructions.comparisons.if_icmp;

import com.jvm.instructions.base.Base;
import com.jvm.instructions.base.BranchInstruction;
import com.jvm.rtda.Frame;
import com.jvm.utils.t.Tuple2;

public class IF_ICMPNE extends BranchInstruction {
    @Override
    public void Execute(Frame frame) {
        Tuple2<Integer, Integer> data = _icmpPop(frame);
        if (!data.getFirst().equals(data.getSecond())) {
            Base.Branch(frame, this.Offset);
        }
    }
}
