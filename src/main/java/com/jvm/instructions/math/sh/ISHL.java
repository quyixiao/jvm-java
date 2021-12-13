package com.jvm.instructions.math.sh;

import com.jvm.instructions.base.NoOperandsInstruction;
import com.jvm.rtda.Frame;
import com.jvm.rtda.OperandStack;

// Shift left int
//先从操作数栈中弹出两个int变量v2和v1。v1是要进行位移操作 的变量，
//v2指出要移位多少比特。位移之后，把结果推入操作数栈。 这里注意两点:
//第一，int变量只有32位，所以只取v2的前5个比特就 足够表示位移位数了;
//第二，Go语言位移操作符右侧必须是无符号 整数，所以需要对v2进行类型转换。
public class ISHL extends NoOperandsInstruction {
    @Override
    public void Execute(Frame frame) {
        OperandStack stack = frame.OperandStack();
        int v2 = stack.PopInt();
        int v1 = stack.PopInt();
        int s = v2 & 0x1f;
        int result = v1 << s;
        stack.PushInt(result);
    }

}
