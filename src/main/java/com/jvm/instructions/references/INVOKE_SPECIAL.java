package com.jvm.instructions.references;

import com.jvm.instructions.base.Index16Instruction;
import com.jvm.rtda.Frame;
// Invoke instance method;
// special handling for superclass, private, and instance initialization method invocations
public class INVOKE_SPECIAL  extends Index16Instruction {
    @Override
    public void Execute(Frame frame) {
        frame.OperandStack().PopRef();
    }
}
