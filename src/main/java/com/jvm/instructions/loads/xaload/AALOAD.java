package com.jvm.instructions.loads.xaload;

import com.jvm.instructions.base.NoOperandsInstruction;
import com.jvm.rtda.Frame;
import com.jvm.rtda.OperandStack;
import com.jvm.rtda.heap.JObject;

// Load reference from array
// 加载指令从局部变量表获取变量，然后推入操作数栈顶
public class AALOAD extends NoOperandsInstruction {
    @Override
    public void Execute(Frame frame) {
        OperandStack stack = frame.OperandStack();
        int index = stack.PopInt() ;          //首先从操作数栈中弹出第一个操作数:数组索引
        JObject arrRef = stack.PopRef();		  //弹出第二个操作数:数组引用

        checkNotNil(arrRef);			//如果数组引用是null，则抛出 NullPointerException异常。
        JObject refs []= arrRef.Refs();
        checkIndex(refs.length, index); //如果数组索引小于0，或者大于等于数组长度，则抛出 ArrayIndexOutOfBoundsException
        stack.PushRef(refs[index])	;	//如果一切正常，则按索引取出数组元素，推入操作数栈顶。
    }
}
