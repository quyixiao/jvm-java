package com.jvm.instructions.math.rem;

import com.jvm.instructions.base.NoOperandsInstruction;
import com.jvm.rtda.Frame;
import com.jvm.rtda.OperandStack;
import com.jvm.utils.ExceptionUtils;

public class LREM extends NoOperandsInstruction {
    @Override
    public void Execute(Frame frame) {
        OperandStack stack = frame.OperandStack();
        long v2 = stack.PopLong();
        long v1 = stack.PopLong();
        if (v2 == 0) {
            ExceptionUtils.throwException("java.lang.ArithmeticException: / by zero");
        }

        long result = v1 % v2;
        stack.PushLong(result);
    }
}
