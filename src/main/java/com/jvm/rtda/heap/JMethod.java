package com.jvm.rtda.heap;

import com.jvm.classfile.CodeAttribute;
import com.jvm.classfile.MemberInfo;

public class JMethod {

    public ClassMember classMember;
    public int maxStack;                    // maxStack和maxLocals字段分别存放操作数栈和局部变量表大 小，这两个值是由Java编译器计算好的。
    public int maxLocals;
    public byte[] code;

    public JMethod() {
        if(this.classMember == null){
            this.classMember = new ClassMember();
        }
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
