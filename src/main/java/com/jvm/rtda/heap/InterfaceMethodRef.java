package com.jvm.rtda.heap;

import com.jvm.classfile.ConstantInterfaceMethodrefInfo;

public class InterfaceMethodRef {

    public MemberRef memberRef;
    public JMethod method;

    public InterfaceMethodRef(JConstantPool cp, ConstantInterfaceMethodrefInfo refInfo) {
        this.memberRef = new MemberRef();
        this.memberRef.symRef.cp = cp;
        this.memberRef.copyMemberRefInfo(refInfo.constantMemberrefInfo);
    }

    public JMethod ResolvedInterfaceMethod() {
        if (this.method == null) {
            this.resolveInterfaceMethodRef();
        }
        return this.method;
    }

    // jvms8 5.4.3.4
    public void resolveInterfaceMethodRef() {
        //class := self.ResolveClass()
        // todo
    }


}
