package com.jvm.instructions.references;

import com.jvm.instructions.base.Index16Instruction;
import com.jvm.rtda.Frame;
import com.jvm.rtda.OperandStack;
import com.jvm.rtda.heap.ClassRef;
import com.jvm.rtda.heap.JClass;
import com.jvm.rtda.heap.JConstantPool;
import com.jvm.rtda.heap.JObject;
import com.jvm.utils.ExceptionUtils;

// Check whether object is of given type
public class CHECK_CAST extends Index16Instruction {

    //如果对象引用不是null，则解析类符号引用，判断对象是否是 类的实例，然后把判断结果推入操作数栈。Java虚拟机规范给出了 具体的判断步骤，
//我们在Object结构体的IsInstanceOf()方法中实 现，稍后给出代码。
//instanceof指令 会改变操作数栈(弹出对象引用，推入判断结果);checkcast则不改 变操作数栈(如果判断失败，直接抛出ClassCastException异常)。
    @Override
    public void Execute(Frame frame) {
        OperandStack stack = frame.OperandStack();
        JObject ref = stack.PopRef();
        stack.PushRef(ref);
        //先从操作数栈中弹出对象引用，再推回去，这样就不会改变操 作数栈的状态。如果引用是null，则指令执行结束。也就是说，null 引用可以转换成任何类型
        if (ref == null) {
            return;
        }
        //则解析类符号引用，判断对象是否是 类的实例。如果是的话，指令执行结束，
        JConstantPool cp = frame.Method().classMember.Class().ConstantPool();
        ClassRef classRef = (ClassRef) cp.GetConstant(this.Index);
        JClass jClass = classRef.symRef.ResolvedClass();
        //否则抛出 ClassCastException。
        if (!ref.IsInstanceOf(jClass)) {
            ExceptionUtils.throwException("java.lang.ClassCastException");
        }
    }
}
