package com.jvm.instructions.constants;

import com.jvm.instructions.base.NoOperandsInstruction;
import com.jvm.rtda.Frame;



//dconst_0指令把double型0推入操作数栈顶
public class DCONST_0 extends NoOperandsInstruction {
    @Override
    public void Execute(Frame frame) {
        frame.OperandStack().PushDouble(0.0);
    }
}
