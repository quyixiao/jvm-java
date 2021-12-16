package com.jvm.rtda.heap;

import com.jvm.classfile.ClassFile;
import com.jvm.classfile.MemberInfo;
import com.jvm.data.Uint16;

public class JClass {
    public Uint16 accessFlags;
    public String name;//              string // thisClassName
    public String superClassName;
    public String[] interfaceNames;
    public JConstantPool constantPool;
    public JField[] fields;
    public JMethod[] methods;
    public JClassLoader loader;
    public JClass superClass;
    public JClass interfaces[];
    public int instanceSlotCount;
    public int staticSlotCount;
    public Slots staticVars;


    public JClass(ClassFile cf) {
        this.accessFlags = cf.AccessFlags();
        this.name = cf.ClassName();
        this.superClassName = cf.SuperClassName();
        this.interfaceNames = cf.InterfaceNames();
        this.constantPool = new JConstantPool(this, cf.constantPool);
        this.fields =newFields(this,cf.fields);
        this.methods = newMethods(this,cf.methods);
    }

    //修改newFields()方法，从字段属性表中读取constValueIndex， 代码改动如下:
    public JField[] newFields(JClass jClass, MemberInfo[] cfFields) {
        JField fields[] = new JField[cfFields.length];
        for (int i = 0; i < fields.length; i++) {
            MemberInfo cfField = cfFields[i];
            fields[i] = new JField();
            fields[i].classMember.jClass = jClass;
            fields[i].classMember.copyMemberInfo(cfField);
            fields[i].copyAttributes(cfField);
        }
        return fields;
    }



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



    public boolean IsPublic() {
        return 0 != (this.accessFlags.Value() & Constants.ACC_PUBLIC);
    }

    public boolean IsFinal() {
        return 0 != (this.accessFlags.Value() & Constants.ACC_FINAL);
    }

    public boolean IsSuper() {
        return 0 != (this.accessFlags.Value() & Constants.ACC_SUPER);
    }

    public boolean IsInterface() {
        return 0 != (this.accessFlags.Value() & Constants.ACC_INTERFACE);
    }

    public boolean IsAbstract() {
        return 0 != (this.accessFlags.Value() & Constants.ACC_ABSTRACT);
    }

    public boolean IsSynthetic() {
        return 0 != (this.accessFlags.Value() & Constants.ACC_SYNTHETIC);
    }

    public boolean IsAnnotation() {
        return 0 != (this.accessFlags.Value() & Constants.ACC_ANNOTATION);
    }

    public boolean IsEnum() {
        return 0 != (this.accessFlags.Value() & Constants.ACC_ENUM);
    }



    // getters
    public JConstantPool ConstantPool()  {
        return this.constantPool;
    }



    public Slots StaticVars() {
         return this.staticVars;
    }

    // jvms 5.4.4
    public boolean isAccessibleTo(JClass other) {
        return this.IsPublic() ||
                this.getPackageName() == other.getPackageName();
    }


    public String getPackageName() {
        int i = this.name.lastIndexOf("/");
        if (i >= 0) {
            return this.name.substring(0, i);
        }
        return "";
    }


    // jvms8 6.5.instanceof
    // jvms8 6.5.checkcast
    public boolean isAssignableFrom(JClass other) {
        JClass s = other;
        JClass t = this;
        if (this == other) {
            return true;
        }
        if (!t.IsInterface()) {
            return s.isSubClassOf(t);
        } else {
            return s.isImplements(t);
        }
    }

    // self extends c
    public boolean isSubClassOf(JClass other) {
        while (true) {
            JClass c = this.superClass;
            if (c != null) {
                if (c == other) {
                    return true;
                }
            } else {
                break;
            }
        }
        return false;
    }

    // self implements iface
// 判断S是否是T的子类，实际上也就是判断T是否是S的(直接或 间接)超类
    public boolean isImplements(JClass iface) {
        JClass c = this;
        while (true) {
            JClass[] jClasses = c.interfaces;
            for (JClass i : jClasses) {
                if (i == iface || i.isSubInterfaceOf(iface)) {
                    return true;
                }
            }
            c = c.superClass;
            if (c == null) {
                break;
            }
        }
        return false;
    }

    // self extends iface
//判断S是否实现了T接口，就看S或S的(直接或间接)超类是否 实现了某个接口T'，T'要么是T，要么是T的子接口。
//isSubInterfaceOf()方法也在class_hierarchy.go文件中
    public boolean isSubInterfaceOf(JClass iface) {
        for (JClass superInterface : this.interfaces) {
            if (superInterface == iface || superInterface.isSubInterfaceOf(iface)) {
                return true;
            }
        }
        return false;
    }

    public JMethod GetMainMethod() {
        return this.getStaticMethod("main", "([Ljava/lang/String;)V");
    }

    public JMethod getStaticMethod(String name, String descriptor) {
        for (JMethod method : this.methods) {
            if (method.classMember.IsStatic() &&
                    method.classMember.name.equals(name) &&
                    method.classMember.descriptor.equals(descriptor)) {
                return method;
            }
        }
        return null;
    }

    //这里只是调用了Object结构体的newObject()方法
    public JObject NewObject()  {
        return new JObject(this);
    }
}
