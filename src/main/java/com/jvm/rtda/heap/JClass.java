package com.jvm.rtda.heap;

import com.jvm.classfile.ClassFile;
import com.jvm.classfile.MemberInfo;
import com.jvm.classfile.SourceFileAttribute;
import com.jvm.data.Uint16;
import com.jvm.utils.ExceptionUtils;
import com.jvm.utils.StringUtils;

import java.util.ArrayList;
import java.util.List;

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
    public boolean initStarted;        //为了判断类是否已经初始化，需要给Class结构体添加一个字段
    public  JObject             jObject;       //通过jClass字段，每个Class结构体实例都与一个类对象关联。
    public  String sourceFile        ;							//最后实现Class结构体的SourceFile()方法和Method结构体的 GetLineNumber()


    public JClass(String name) {
        this.name = name;
    }

    public JClass(ClassFile cf) {
        this.accessFlags = cf.AccessFlags();
        this.name = cf.ClassName();
        this.superClassName = cf.SuperClassName();
        this.interfaceNames = cf.InterfaceNames();
        this.constantPool = new JConstantPool(this, cf.constantPool);
        this.fields = newFields(this, cf.fields);
        this.methods = newMethods(this, cf.methods);
        this.sourceFile = getSourceFile(cf)	;	//从class文件中读取源文件名
    }

    public JClass(Uint16 accessFlags,String name,JClassLoader loader ,boolean initStarted, JClass superClass ,JClass interfaces[] ) {
        this.accessFlags = accessFlags;
        this.name = name;
        this.loader = loader;
        this.initStarted = initStarted;
        this.superClass = superClass;
        this.interfaces = interfaces;
    }

    public JClass(Uint16 accessFlags, String name, JClassLoader loader, boolean initStarted) {
        this.accessFlags = accessFlags;
        this.name = name;
        this.loader = loader;
        this.initStarted = initStarted;
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
            methods[i].calcArgSlotCount();
            MethodDescriptorParser parser = new MethodDescriptorParser();
            MethodDescriptor md = parser.parseMethodDescriptor(methods[i].classMember.descriptor);
            //先计算argSlotCount字段，如果是本地 方法，则注入字节码和其他信息。
            if (methods[i].IsNative() ){
                methods[i].injectCodeAttribute(md.returnType);
            }
        }
        return methods;
    }



    //源文件名在ClassFile结构的属性表中， getSourceFile()函数提取这个信息
//【注意】并不是每个class文件中都有源文件信息，这个因编译时 的编译器选项而异。
    public String  getSourceFile(ClassFile cf ) {
        SourceFileAttribute sfAttr = cf.SourceFileAttribute();
        if ( sfAttr != null) {
            return sfAttr.FileName();
        }
        return "Unknown" ;// todo
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
    public JConstantPool ConstantPool() {
        return this.constantPool;
    }


    public Slots StaticVars() {
        return this.staticVars;
    }

    // jvms 5.4.4
    public boolean isAccessibleTo(JClass other) {
        return this.IsPublic() ||
                this.getPackageName() .equals(other.getPackageName());
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
        JClass c = this.superClass;
        if (c == null) {
            return false;
        }
        while (true) {
            if (c == other) {
                return true;
            }
            c = c.superClass;
            if (c == null) {
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
        if(this.interfaces ==null){
            return false;
        }
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
    public JObject NewObject() {
        return new JObject(this);
    }


    //InitStarted()是Getter方法，返回initStarted字段值
    public boolean InitStarted() {
        return this.initStarted;
    }

    //StartInit()方 法把initStarted字段设置成true。
    public void StartInit() {
        this.initStarted = true;
    }

    public JMethod GetClinitMethod() {
        return this.getStaticMethod("<clinit>", "()V");
    }

    public JClass SuperClass() {
        return this.superClass;
    }


    // self implements iface
    public boolean IsImplements(JClass iface) {
        JClass c = this;
        if(c == null){
            return false;
        }
        while (true) {
            if(c.interfaces !=null){
                for (JClass i : c.interfaces) {
                    if (i == iface || i.isSubInterfaceOf(iface)) {
                        return true;
                    }
                }
            }
            c = c.superClass;
            if (c == null) {
                break;
            }
        }
        return false;
    }

    // c extends self
    public boolean IsSuperClassOf(JClass other) {
        return other.IsSubClassOf(this);
    }

    // self extends c
    public boolean IsSubClassOf(JClass other) {
        JClass c = this.superClass;
        while (true) {
            if (c == other) {
                return true;
            }
            c = c.superClass;
            if (c == null) {
                break;
            }
        }
        return false;
    }


    public JMethod getMethod(String name, String descriptor, boolean isStatic) {
        JClass c = this;
        while (true) {
            for (JMethod method : c.methods) {
                if (method.classMember.IsStatic() == isStatic &&
                        method.classMember.name.equals(name) &&
                        method.classMember.descriptor.equals(descriptor)) {
                    return method;
                }
            }
            c = c.superClass;
            if (c == null) {
                break;
            }
        }
        return null;
    }


    public JField getField(String name, String descriptor, boolean isStatic) {
        JClass c = this;
        while (true) {
            for (JField field : c.fields) {
                if (field.classMember.IsStatic() == isStatic &&
                        field.classMember.name.equals(name) &&
                        field.classMember.descriptor.equals(descriptor)) {

                    return field;
                }
            }
            c = c.superClass;
            if (c == null) {
                break;
            }
        }
        return null;
    }


    public boolean isJlObject() {
        return this.name.equals("java/lang/Object");
    }

    public boolean isJlCloneable() {
        return this.name.equals("java/lang/Cloneable");
    }

    public boolean isJioSerializable() {
        return this.name.equals("java/io/Serializable");
    }


    //比如类名是java/lang/Object，则它的包名就是java/lang。如果类 定义在默认包中，它的包名是空字符串。
    public String GetPackageName() {
        int i = this.name.indexOf("/");
        if (i > 0) {
            return this.name.substring(0, i);
        }
        return "";
    }


    public JClass ArrayClass() {
        String arrayClassName = ClassNameHelper.getArrayClassName(this.name);
        return this.loader.LoadClass(arrayClassName);
    }

    public String JavaName() {
        return this.name.replaceAll("/", ".");
    }

    public boolean IsPrimitive() {
        String d = ClassNameHelper.primitiveTypes.get(this.name);
        return StringUtils.isNotBlank(d);
    }

    public JMethod GetInstanceMethod(String name, String descriptor) {
        return this.getMethod(name, descriptor, false);
    }

    public JMethod GetStaticMethod(String name, String descriptor) {
        return this.getMethod(name, descriptor, true);
    }

    // reflection
    public JObject GetRefVar(String fieldName, String fieldDescriptor) {
        JField field = this.getField(fieldName, fieldDescriptor, true);
        return this.staticVars.GetRef(field.slotId);
    }

    public void SetRefVar(String fieldName, String fieldDescriptor, JObject ref) {
        JField field = this.getField(fieldName, fieldDescriptor, true);
        this.staticVars.SetRef(field.slotId, ref);
    }


    public JField[] GetFields(boolean publicOnly) {
        if (publicOnly) {
            List<JField> publicFields = new ArrayList<>();
            for (JField field : this.fields) {
                if (field.classMember.IsPublic()) {
                    publicFields.add(field);
                }
            }
            return publicFields.toArray(new JField[publicFields.size()]);
        } else {
            return this.fields;
        }
    }

    public JObject JObject() {
        return this.jObject;
    }

    public JMethod GetConstructor(String descriptor) {
        return this.GetInstanceMethod("<init>", descriptor);
    }

    public JMethod[] GetConstructors(boolean publicOnly) {
        List<JMethod> constructors = new ArrayList<>();
        for (JMethod method : this.methods) {
            if (method.isConstructor()) {
                if (!publicOnly || method.classMember.IsPublic()) {
                    constructors.add(method);
                }
            }
        }
        return constructors.toArray(new JMethod[constructors.size()]);
    }

    public JMethod[] GetMethods(boolean publicOnly) {
        List<JMethod> methods = new ArrayList<>();
        for (int i = 0; i < this.methods.length; i++) {
            JMethod method = this.methods[i];
            if (!method.isClinit() && !method.isConstructor()) {
                if (!publicOnly || method.classMember.IsPublic()) {
                    methods.add(method);
                }
            }
        }
        return methods.toArray(new JMethod[methods.size()]);
    }


    public boolean IsArray() {
        return this.name.toCharArray()[0] == '[';
    }


    public String  Name() {
        return this.name;
    }

    public JClass ComponentClass() {
        String componentClassName = ClassNameHelper.getComponentClassName(this.name);
        return this.loader.LoadClass(componentClassName);
    }

    //NewArray()方法专门用来创建数组对象
    public JObject NewArray(int count ) {
        //如果类并不是数组类，就调用panic()函数终止程序执行，否则根据数组类型创建数组对象。
        if (!this.IsArray() ){
            ExceptionUtils.throwException("Not array class: " + this.name);
        }
        switch (this.Name() ){
            case "[Z":
                return new JObject(this, new byte[count], null);
            case "[B":
                return new JObject(this, new byte[ count], null);
            case "[C":
                return new JObject(this,new char[ count], null);
            case "[S":
                return new JObject(this, new short[ count], null);
            case "[I":
                return new JObject(this, new int [ count], null);
            case "[J":
                return new JObject(this, new long [count], null);
            case "[F":
                return new JObject(this, new float[ count], null);
            case "[D":
                return new JObject(this, new double [ count], null);
            default:
                return new JObject(this,new JObject[ count], null);
        }
    }

    public static JObject NewByteArray(JClassLoader loader,byte  bytes []) {
        return new JObject(loader.LoadClass("[B"), bytes, null);
    }


    // jvms8 6.5.instanceof
// jvms8 6.5.checkcast
    public boolean IsAssignableFrom( JClass other) {
        JClass s = other;
        JClass t =  this;
        if (s == t ){
            return true;
        }
        if (!s.IsArray() ){
            if (!s.IsInterface()) {
                // s is class
                if (!t.IsInterface() ){
                    // t is not interface
                    return s.IsSubClassOf(t);
                } else {
                    // t is interface
                    return s.IsImplements(t);
                }
            } else {
                // s is interface
                if (!t.IsInterface() ){
                    // t is not interface
                    return t.isJlObject();
                } else {
                    // t is interface
                    return t.isSuperInterfaceOf(s);
                }
            }
        } else {
            // s is array
            if (!t.IsArray() ){
                if (!t.IsInterface() ){
                    // t is class
                    return t.isJlObject();
                } else {
                    // t is interface
                    return t.isJlCloneable() || t.isJioSerializable();
                }
            } else {
                // t is array
                JClass sc = s.ComponentClass();
                JClass tc = t.ComponentClass();
                return sc == tc || tc.IsAssignableFrom(sc);
            }
        }
    }

    public JClassLoader Loader()  {
        return this.loader;
    }

    // iface extends self
    public boolean isSuperInterfaceOf(JClass iface) {
        return iface.isSubInterfaceOf(this);
    }


    // getters
    public Uint16 AccessFlags() {
        return this.accessFlags;
    }

    public JClass [] Interfaces() {
        return this.interfaces;
    }

    public String  SourceFile() {
        return this.sourceFile;
    }

}
