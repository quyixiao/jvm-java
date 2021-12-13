package com.jvm.instructions.math.div;

import com.jvm.instructions.base.NoOperandsInstruction;
import com.jvm.rtda.Frame;
import com.jvm.rtda.OperandStack;
import com.jvm.utils.ExceptionUtils;

// Divide long
public class LDIV extends NoOperandsInstruction {
    @Override
    public void Execute(Frame frame) {
        OperandStack stack = frame.OperandStack();
        Long v2 = stack.PopLong();
        Long v1 = stack.PopLong();
        if (v2 == 0) {
            ExceptionUtils.throwException("java.lang.ArithmeticException: / by zero");
        }

        Long result = v1 / v2;
        stack.PushLong(result);
    }
}
