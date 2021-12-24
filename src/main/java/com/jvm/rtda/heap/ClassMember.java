package com.jvm.rtda.heap;

import com.jvm.classfile.MemberInfo;
import com.jvm.data.Uint16;

public class ClassMember {

    public Uint16 accessFlags;
    public String name;
    public String descriptor;
    public JClass jClass;                    //class字段存放 Class结构体指针，这样可以通过字段或方法访问到它所属的类。

    public  String signature      ;
    public  byte [] annotationData ;// RuntimeVisibleAnnotations_attribute

    //copyMemberInfo()方法从class文件中复制数据
    public void copyMemberInfo(MemberInfo memberInfo) {
        this.accessFlags = memberInfo.AccessFlags();
        this.name = memberInfo.Name();
        this.descriptor = memberInfo.Descriptor();
    }


    public ClassMember() {
    }

    public ClassMember(Uint16 accessFlags, String name, JClass jClass) {
        this.accessFlags = accessFlags;
        this.name = name;
        this.jClass = jClass;
    }

    public boolean IsPublic() {
        return 0 != (this.accessFlags.Value() & Constants.ACC_PUBLIC);
    }

    public boolean IsPrivate() {
        return 0 != (this.accessFlags.Value() & Constants.ACC_PRIVATE);
    }

    public boolean IsProtected() {
        return 0 != (this.accessFlags.Value() & Constants.ACC_PROTECTED);
    }

    public boolean IsStatic() {
        return 0 != (this.accessFlags.Value() & Constants.ACC_STATIC);
    }

    public boolean IsFinal() {
        return 0 != (this.accessFlags.Value() & Constants.ACC_FINAL);
    }

    public boolean IsSynthetic() {
        return 0 != (this.accessFlags.Value() & Constants.ACC_SYNTHETIC);
    }

    // getters
    public Uint16 AccessFlags()  {
        return this.accessFlags;
    }

    // getters
    public String Name() {
        return this.name;
    }

    public String Descriptor() {
        return this.descriptor;
    }


    public  String   Signature()  {
        return this.signature;
    }
   public byte []  AnnotationData() {
        return this.annotationData;
    }


    public JClass Class() {
        return this.jClass;
    }

    // jvms 5.4.4
    public boolean isAccessibleTo(JClass d) {
        if (this.IsPublic()) {
            return true;
        }

        JClass c = this.jClass;

        if (this.IsProtected()) {
            return d == c || d.isSubClassOf(c) ||
                    c.getPackageName().equals( d.getPackageName()) ;
        }
        if (!this.IsPrivate()) {
            return c.getPackageName() .equals(d.getPackageName());
        }
        return d == c;
    }


}
