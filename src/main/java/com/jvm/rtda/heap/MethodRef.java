package com.jvm.rtda.heap;

import com.jvm.classfile.ConstantMethodrefInfo;
import com.jvm.utils.ExceptionUtils;

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
    public void  resolveMethodRef() {
        JClass d = this.memberRef.symRef.cp.jClass;
        JClass c = this.memberRef.symRef.ResolvedClass();
        //如果类D想通过方法符号引用访问类C的某个方法，先要解析
        // 符号引用得到类C。如果C是接口，则抛出 IncompatibleClassChangeError异常
        if( c.IsInterface() ){
            ExceptionUtils.throwException("java.lang.IncompatibleClassChangeError");
        }
        // 如果找不到对应的方法，则抛出NoSuchMethodError异常，否 则检查类D是否有权限访问该方法。如果没有，则抛出 IllegalAccessError异常。
        // isAccessibleTo()方法是在ClassMember结构 体中定义的，在第6章就已经实现了。
        JMethod method = lookupMethod(c, this.memberRef.name, this.memberRef.descriptor);
        if (method == null) {
            ExceptionUtils.throwException("java.lang.NoSuchMethodError");
        }
        if( !method.classMember.isAccessibleTo(d)) {
            ExceptionUtils.throwException("java.lang.IllegalAccessError");
        }
        this.method = method;
    }


    JMethod  lookupMethod(JClass jClass,String  name,String  descriptor ) {
        JMethod method = MethodLookup.LookupMethodInClass(jClass, name, descriptor);
        if (method == null){
            method = MethodLookup.lookupMethodInInterfaces(jClass.interfaces, name, descriptor);
        }
        return method;
    }


}
