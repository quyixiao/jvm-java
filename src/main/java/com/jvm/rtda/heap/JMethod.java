package com.jvm.rtda.heap;

import com.jvm.classfile.CodeAttribute;
import com.jvm.classfile.MemberInfo;

public class JMethod {

    public ClassMember classMember;
    public int maxStack;                    // maxStack和maxLocals字段分别存放操作数栈和局部变量表大 小，这两个值是由Java编译器计算好的。
    public int maxLocals;
    public byte[] code;


    //code字段存放方法字节码。 newMethods()函数根据class文件中的方法信息创建Method表
    public JMethod[] newMethods(JClass jClass, MemberInfo[] cfMethods) {
        JMethod methods[] = new JMethod[cfMethods.length];
        for (int i = 0; i < cfMethods.length; i++) {
            MemberInfo cfMethod = cfMethods[i];
            methods[i] = new JMethod();
            methods[i].classMember.jClass = jClass;
            methods[i].classMember.copyMemberInfo(cfMethod);
            methods[i].copyAttributes(cfMethod);//maxStack、maxLocals和字节码在class文件中 是以属性的形式存储在method_info结构中的。
        }
        return methods;
    }


    public void copyAttributes(MemberInfo cfMethod) {
        CodeAttribute codeAttr = cfMethod.CodeAttribute();
        if (codeAttr != null) {
            this.maxStack = codeAttr.MaxStack();
            this.maxLocals = codeAttr.MaxLocals();
            this.code = codeAttr.Code();
        }
    }

    public boolean IsSynchronized() {
        return 0 != (this.classMember.accessFlags.Value() & Constants.ACC_SYNCHRONIZED);
    }

    public boolean IsBridge() {
        return 0 != (this.classMember.accessFlags.Value() & Constants.ACC_BRIDGE);
    }

    public boolean IsVarargs() {
        return 0 != (this.classMember.accessFlags.Value() & Constants.ACC_VARARGS);
    }

    public boolean IsNative() {
        return 0 != (this.classMember.accessFlags.Value() & Constants.ACC_NATIVE);
    }

    public boolean IsAbstract() {
        return 0 != (this.classMember.accessFlags.Value() & Constants.ACC_ABSTRACT);
    }

    public boolean IsStrict() {
        return 0 != (this.classMember.accessFlags.Value() & Constants.ACC_STRICT);
    }

    // getters
    public int MaxStack() {
        return this.maxStack;
    }

    public int MaxLocals() {
        return this.maxLocals;
    }

    public byte[] Code() {
        return this.code;
    }


}
