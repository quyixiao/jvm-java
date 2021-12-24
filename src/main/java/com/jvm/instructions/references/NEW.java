package com.jvm.instructions.references;

import com.jvm.instructions.base.ClassInitLogic;
import com.jvm.instructions.base.Index16Instruction;
import com.jvm.rtda.Frame;
import com.jvm.rtda.heap.ClassRef;
import com.jvm.rtda.heap.JClass;
import com.jvm.rtda.heap.JConstantPool;
import com.jvm.rtda.heap.JObject;
import com.jvm.utils.ExceptionUtils;

public class NEW extends Index16Instruction {
    @Override
    public void Execute(Frame frame) {
        JConstantPool cp = frame.Method().classMember.Class().ConstantPool();
        ClassRef classRef = (ClassRef) cp.GetConstant(this.Index);
        JClass jClass = classRef.symRef.ResolvedClass();
        if (!jClass.InitStarted() ){
            frame.RevertNextPC();
            //另外，如果解析后的类还没有初始化，则需要先初始化类。 在第7章实现方法调用之后会详细讨论类的初始化，这里暂时先忽 略。
            ClassInitLogic.InitClass(frame.Thread(), jClass);
            return;
        }

        //因为接口和抽象类都不能实例化，所以如果解析后的类是接 口或抽象类，按照Java虚拟机规范规定，需要抛出InstantiationError 异常。
        if (jClass.IsInterface() || jClass.IsAbstract()) {
            ExceptionUtils.throwException("java.lang.InstantiationError");
        }

        JObject ref = jClass.NewObject();
        frame.OperandStack().PushRef(ref);
    }
}
