package com.jvm.instructions.stores.xastore;


import com.jvm.instructions.base.NoOperandsInstruction;
import com.jvm.rtda.Frame;
import com.jvm.rtda.OperandStack;
import com.jvm.rtda.heap.JObject;

// Store into reference array
public class AASTORE extends NoOperandsInstruction {


    @Override
    public void Execute(Frame frame) {
        OperandStack stack = frame.OperandStack();
        JObject ref = stack.PopRef();        //要赋给数组元素的值
        int index = stack.PopInt();            //数组索引
        JObject arrRef = stack.PopRef();    //数组引用

        checkNotNil(arrRef);                //如果数组引用是null，则 抛出NullPointerException。
        JObject refs[] = arrRef.Refs();
        checkIndex(refs.length, index);        //如果数组索引小于0或者大于等于数组 长度，则抛出ArrayIndexOutOfBoundsException异常。
        refs[index] = ref;
    }
}
