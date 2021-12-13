package com.jvm.instructions.conversions.i2x;

import com.jvm.instructions.base.NoOperandsInstruction;
import com.jvm.rtda.Frame;
import com.jvm.rtda.OperandStack;
import lombok.Data;


// Convert int to float
@Data
public class I2F extends NoOperandsInstruction {
    @Override
    public void Execute(Frame frame) {
        OperandStack stack = frame.OperandStack();
        int i = stack.PopInt();
        float f = i;
        stack.PushFloat(f);
    }
}
