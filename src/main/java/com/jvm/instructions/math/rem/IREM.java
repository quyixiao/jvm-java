package com.jvm.instructions.math.rem;

import com.jvm.instructions.base.NoOperandsInstruction;
import com.jvm.rtda.Frame;
import com.jvm.rtda.OperandStack;
import com.jvm.utils.ExceptionUtils;

public class IREM extends NoOperandsInstruction {
    @Override
    public void Execute(Frame frame) {
        OperandStack stack = frame.OperandStack();
        int v2 = stack.PopInt();
        int v1 = stack.PopInt();
        if (v2 == 0) {
            ExceptionUtils.throwException("java.lang.ArithmeticException: / by zero");
        }

        int result = v1 % v2;
        stack.PushInt(result);
    }
}
