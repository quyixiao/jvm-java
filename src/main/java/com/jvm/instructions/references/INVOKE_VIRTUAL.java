package com.jvm.instructions.references;

import com.jvm.instructions.base.Index16Instruction;
import com.jvm.rtda.Frame;
import com.jvm.rtda.OperandStack;
import com.jvm.rtda.heap.JConstantPool;
import com.jvm.rtda.heap.MethodRef;
import com.jvm.utils.ExceptionUtils;

// Invoke instance method; dispatch based on class
public class INVOKE_VIRTUAL extends Index16Instruction {
    @Override
    public void Execute(Frame frame) {
       JConstantPool  cp = frame.Method().classMember.Class().ConstantPool();
        MethodRef methodRef = (MethodRef) cp.GetConstant(this.Index);
        if (methodRef.memberRef.Name().equals("println")) {
            OperandStack stack=frame.OperandStack();
            switch (methodRef.memberRef.Descriptor()) {
                case "(Z)V":
                    System.out.println( stack.PopInt() != 0);
                    break;
                case "(C)V":
                    System.out.println( stack.PopInt());
                    break;
                case "(I)V":
                case "(B)V":
                case "(S)V":
                    System.out.println( stack.PopInt());
                    break;
                case "(F)V":
                    System.out.println( stack.PopFloat());
                    break;
                case "(J)V":
                    System.out.println(stack.PopLong());
                    break;
                case "(D)V":
                    System.out.println( stack.PopDouble());
                    break;
                default:
                    ExceptionUtils.throwException( methodRef.memberRef.Descriptor());
            }
            stack.PopRef();
        }
    }
}
