package com.jvm.instructions.loads.xaload;


import com.jvm.instructions.base.NoOperandsInstruction;
import com.jvm.rtda.Frame;
import com.jvm.rtda.OperandStack;
import com.jvm.rtda.heap.JObject;
import lombok.Data;

// Load char from array
@Data
public class CALOAD extends NoOperandsInstruction {

    @Override
    public void Execute(Frame frame) {
        OperandStack stack = frame.OperandStack();
        int index = stack.PopInt();
        JObject arrRef = stack.PopRef();

        checkNotNil(arrRef);
        char chars[] = arrRef.Chars();
        checkIndex(chars.length, index);
        stack.PushInt((int) (chars[index]));
    }
}
