package com.jvm.instructions.math.div;

import com.jvm.instructions.base.NoOperandsInstruction;
import com.jvm.rtda.Frame;
import com.jvm.rtda.OperandStack;
import com.jvm.utils.ExceptionUtils;

// Divide int
public class IDIV extends NoOperandsInstruction {
    @Override
    public void Execute(Frame frame) {
        OperandStack stack = frame.OperandStack();
        Integer v2 = stack.PopInt();
        Integer v1 = stack.PopInt();
        if (v2 == 0 ){
            ExceptionUtils.throwException("java.lang.ArithmeticException: / by zero");
        }

        Integer result = v1 / v2;
        stack.PushInt(result);
    }
}
