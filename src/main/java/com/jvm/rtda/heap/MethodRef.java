package com.jvm.rtda.heap;

import com.jvm.classfile.ConstantMethodrefInfo;

public class MethodRef {

    public MemberRef memberRef;
    public JMethod method;


    public MethodRef(JConstantPool cp, ConstantMethodrefInfo refInfo) {
        this.memberRef = new MemberRef();
        this.memberRef.symRef.cp = cp;
        this.memberRef.copyMemberRefInfo(refInfo.constantMemberrefInfo);
    }


    public JMethod ResolvedMethod() {
        if (this.method == null) {
            this.resolveMethodRef();
        }
        return this.method;
    }

    // jvms8 5.4.3.3
    public void resolveMethodRef() {
        //class := self.Class()
        // todo
    }


}
