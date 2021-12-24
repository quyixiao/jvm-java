package com.jvm.instructions.references.array;

import com.jvm.instructions.base.Index16Instruction;
import com.jvm.rtda.Frame;
import com.jvm.rtda.OperandStack;
import com.jvm.rtda.heap.ClassRef;
import com.jvm.rtda.heap.JClass;
import com.jvm.rtda.heap.JConstantPool;
import com.jvm.rtda.heap.JObject;
import com.jvm.utils.ExceptionUtils;

// Create new array of reference
public class ANEW_ARRAY extends Index16Instruction {

    public  static int i = 0 ;
    @Override
    public void Execute(Frame frame) {
        JConstantPool cp = frame.Method().classMember.Class().ConstantPool();
        //anewarray指令也需要两个操作数。第一个操作数是uint16索 引，来自字节码。通过这个索引可以从当前类的运行时常量池中找 到一个类符号引用，
        // 解析这个符号引用就可以得到数组元素的类。
        ClassRef classRef = (ClassRef) cp.GetConstant(this.Index);
        JClass componentClass = classRef.symRef.ResolvedClass();

        OperandStack stack = frame.OperandStack();
        //第二个操作数是数组长度，从操作数栈中弹出。
        int count = stack.PopInt();
        if (count < 0) {
            ExceptionUtils.throwException("java.lang.NegativeArraySizeException");
        }
        i ++;
        if(i == 3 ){
            System.out.println("xxxxxxxxxxxxxxx");
        }

        JClass arrClass = componentClass.ArrayClass();
        //数组元素的类型和数组长度创建引用类型数组
        JObject arr = arrClass.NewArray(count);
        stack.PushRef(arr);
    }
}
