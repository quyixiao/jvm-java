package com.jvm.instructions.conversions;

import com.jvm.instructions.base.NoOperandsInstruction;
import com.jvm.rtda.Frame;
import com.jvm.rtda.OperandStack;
import lombok.Data;

@Data
public class I2D extends NoOperandsInstruction {
    @Override
    public void Execute(Frame frame) {
        OperandStack stack = frame.OperandStack();
        int i = stack.PopInt();
        double d = i;
        stack.PushDouble(d);
    }
}
