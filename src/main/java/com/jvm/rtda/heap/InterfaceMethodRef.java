package com.jvm.rtda.heap;

import com.jvm.classfile.ConstantInterfaceMethodrefInfo;
import com.jvm.utils.ExceptionUtils;

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
        JClass d = this.memberRef.symRef.cp.jClass;
        JClass c = this.memberRef.symRef.ResolvedClass();
        if (!c.IsInterface()) {
            ExceptionUtils.throwException("java.lang.IncompatibleClassChangeError");
        }

        JMethod method = lookupInterfaceMethod(c, this.memberRef.name, this.memberRef.descriptor);
        if (method == null) {
            ExceptionUtils.throwException("java.lang.NoSuchMethodError");
        }
        if (!method.classMember.isAccessibleTo(d)) {
            ExceptionUtils.throwException("java.lang.IllegalAccessError");
        }

        this.method = method;
    }

//如果能在接口中找到方法，就返回找到的方法，否则调用 lookupMethodInInterfaces()函数在超接口中寻找。
    public JMethod lookupInterfaceMethod(JClass iface, String name, String descriptor) {
        for (JMethod method : iface.methods) {
            if (method.classMember.name.equals(name) && method.classMember.descriptor.equals(descriptor)) {
                return method;
            }
        }
        return MethodLookup.lookupMethodInInterfaces(iface.interfaces, name, descriptor);
    }

}
