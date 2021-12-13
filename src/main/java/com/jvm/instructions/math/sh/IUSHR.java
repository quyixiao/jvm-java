package com.jvm.instructions.math.sh;

import com.jvm.instructions.base.NoOperandsInstruction;
import com.jvm.rtda.Frame;
import com.jvm.rtda.OperandStack;

//Go语言并没有Java语言中的>>>运算符，为了达到无符号位移 的目的，需要先把v1转成无符号整数，位移操作之后，再转回有符 号整数。
public class IUSHR extends NoOperandsInstruction {


    @Override
    public void Execute(Frame frame) {
        OperandStack stack = frame.OperandStack();
        int v2 = stack.PopInt();
        int v1 = stack.PopInt();
        int s = v2 & 0x1f;
        int result = v1 >> s;
        stack.PushInt(result);
    }


}
