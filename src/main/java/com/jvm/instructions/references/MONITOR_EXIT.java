package com.jvm.instructions.references;

import com.jvm.instructions.base.NoOperandsInstruction;
import com.jvm.rtda.Frame;
import com.jvm.rtda.heap.JObject;
import com.jvm.utils.ExceptionUtils;

// Exit monitor for object
public class MONITOR_EXIT extends NoOperandsInstruction {
    @Override
    public void Execute(Frame frame) {
        JObject ref = frame.OperandStack().PopRef();
        if (ref == null) {
            ExceptionUtils.throwException("java.lang.NullPointerException");
        }
    }
}
