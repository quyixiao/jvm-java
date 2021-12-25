package com.jvm.rtda.heap;

import com.jvm.classfile.ClassFile;
import com.jvm.classpath.Classpath;
import com.jvm.data.Uint16;
import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.Map;

/*
class names:
    - primitive types: boolean, byte, int ...
    - primitive arrays: [Z, [B, [I ...
    - non-array classes: java/lang/Object ...
    - array classes: [Ljava/lang/Object; ...
*/
@Slf4j
public class JClassLoader {


    public Classpath cp; // ClassLoader依赖Classpath来搜索和读取class文件，cp字段保存 Classpath指针
    public Map<String, JClass> classMap;   // loaded classes	//classMap字段记录已经加载的类数据，key是类的完 全限定名。
    public  boolean verboseFlag ;


    public JClassLoader(Classpath cp,   boolean verboseFlag) {
        this.cp = cp;
        this.classMap = new HashMap<>();
        this.verboseFlag = verboseFlag;

        this.loadBasicClasses();
        this.loadPrimitiveClasses();
    }

    //loadBasicClasses()函数先加载java.lang.Class类，这又会触发
    //java.lang.Object等类和接口的加载。然后遍历classMap，给已经加载 的每一个类关联类对象。
   public void  loadBasicClasses() {
        JClass jlClassClass = this.LoadClass("java/lang/Class");
        for (Map.Entry<String ,JClass> map :  this.classMap.entrySet()) {
            JClass clazz = map.getValue();
            if (clazz.jObject == null) {
                clazz.jObject = jlClassClass.NewObject();
                clazz.jObject.extra = clazz;
            }
        }
    }

    public void  loadPrimitiveClasses() {
        for ( Map.Entry<String,String> primitiveType : ClassNameHelper. primitiveTypes .entrySet()){
            //loadPrimitiveClasses()方法加载void和基本类型的类
            this.loadPrimitiveClass(primitiveType.getKey());
        }
    }

    //第一，void和基本类型的类名就是void、 int、float等。
    //第二，基本类型的类没有超类，也没有实现任何接口。
    //第三，非基本类型的类对象是通过ldc指令加载到操作数栈中的
    //而基本类型的类对象，虽然在Java代码中看起来是通过字面量获取的，但是编译之后的指令
    //并不是ldc，而是getstatic。
    //每个基本类型都有一个包装类，包装类中 有一个静态常量，叫作TYPE，其中存放的就是基本类型的类
    //也就是说，基本类型的类是通过getstatic指令访问相应包装类 的TYPE字段加载到操作数栈中的
    public void loadPrimitiveClass(String className) {
        JClass clazz = new JClass(
                new Uint16(Constants.ACC_PUBLIC),
                className,
                this,
                true);
        clazz.jObject = this.classMap.get("java/lang/Class").NewObject();
        clazz.jObject.extra = clazz;
        this.classMap.put(className, clazz);
    }

    public JClass LoadClass(String name) {
        JClass jClass = this.classMap.get(name);
        if (jClass != null) {
            return jClass;
        }
        JClass clazz = null;
        if (name.toCharArray()[0] == '[') { // array class ,如果是数组类
            clazz = this.loadArrayClass(name);
        } else {
            clazz = this.loadNonArrayClass(name);
        }
        JClass jlClassClass = this.classMap.get("java/lang/Class");
        if (jlClassClass != null) {
            clazz.jObject = jlClassClass.NewObject();
            clazz.jObject.extra = clazz;
        }
        return clazz;
    }


    public JClass loadArrayClass(String name) {
        JClass clazz = new JClass(
                new Uint16(Constants.ACC_PUBLIC),
                name,
                this,
                true,                        //因为数组类不需要 初始化，所以把initStarted字段设置成true。
                this.LoadClass("java/lang/Object"),    //数组类的超类是 java.lang.Object，
                new JClass[]{    //并且实现了java.lang.Cloneable和java.io.Serializable 接口。
                        this.LoadClass("java/lang/Cloneable"),
                        this.LoadClass("java/io/Serializable")
                }
        );
        this.classMap.put(name, clazz);
        return clazz;
    }


    public JClass loadNonArrayClass(String name) {
        byte[] data = this.cp.readClass(name);
        JClass jClass = this.defineClass(data);
        link(jClass);
        if ("java/lang/Float".equals(name)) {
            System.out.println("xxxxxxxxxxxxx");
        }
        log.info("[Loaded " + name + " from ]");
        return jClass;
    }

    public JClass defineClass(byte[] data) {
        JClass jClass = parseClass(data);
        jClass.loader = this;
        resolveSuperClass(jClass);
        resolveInterfaces(jClass);
        this.classMap.put(jClass.name, jClass);
        return jClass;
    }


    public JClass parseClass(byte[] data) {
        ClassFile cf = ClassFile.Parse(data);
        return new JClass(cf);
    }


    // jvms 5.4.3.1
//除java.lang.Object以外，所有的类都有且仅有一个 超类。因此，除非是Object类，否则需要递归调用LoadClass()方法 加载它的超类
    public void resolveSuperClass(JClass jClass) {
        if (!"java/lang/Object".equals(jClass.name)) {
            jClass.superClass = jClass.loader.LoadClass(jClass.superClassName);
        }
    }

    //resolveInterfaces()函数递归调用 LoadClass()方法加载类的每一个直接接口
    public void resolveInterfaces(JClass jClass) {
        int interfaceCount = jClass.interfaceNames.length;
        if (interfaceCount > 0) {
            jClass.interfaces = new JClass[interfaceCount];
            for (int i = 0; i < jClass.interfaceNames.length; i++) {
                jClass.interfaces[i] = jClass.loader.LoadClass(jClass.interfaceNames[i]);
            }
        }
    }

    //类的链接分为验证和准备两个必要阶段，link()方法的代码
    public void link(JClass jClass) {
        //为了确保安全性，Java虚拟机规范要求在执行类的任何代码之 前，对类进行严格的验证。
        verify(jClass);
        prepare(jClass);
    }

    public void verify(JClass jClass) {

    }

    // jvms 5.4.2
    public void prepare(JClass jClass) {
        //calcInstanceFieldSlotIds()函数计算实例字段的个数，同时给它们编号
        calcInstanceFieldSlotIds(jClass);
        calcStaticFieldSlotIds(jClass);
        allocAndInitStaticVars(jClass);
    }


    //第一个问题比较好解决，只要数一下类的字段即可。假设某个 类有m个静态字段和n个实例字段，那么静态变量和实例变量所需 的空间大小就分别是m'和n'。
//这里要注意两点。首先，类是可以继承 的。也就是说，在数实例变量时，要递归地数超类的实例变量;其 次，long和double字段都占据两个位置，所以m'>=m，n'>=n。
    public void calcInstanceFieldSlotIds(JClass jClass) {
        int slotId = 0;
        if (jClass.superClass != null) {
            slotId = jClass.superClass.instanceSlotCount;
        }
        for (JField field : jClass.fields) {
            if (!field.classMember.IsStatic()) {
                field.slotId = slotId;
                slotId++;
                if (field.isLongOrDouble()) {
                    slotId++;
                }
            }
        }
        jClass.instanceSlotCount = slotId;
    }


    public void calcStaticFieldSlotIds(JClass jClass) {
        int slotId = 0;
        for (JField field : jClass.fields) {
            if (field.classMember.IsStatic()) {
                field.slotId = slotId;
                slotId++;
                //Field结构体的isLongOrDouble()方法返回字段是否是long或 double类型
                if (field.isLongOrDouble()) {
                    slotId++;
                }
            }
        }
        jClass.staticSlotCount = slotId;
    }

    //allocAndInitStaticVars()函数给类变量分配空间，然后给它们赋 予初始值
    public void allocAndInitStaticVars(JClass jClass) {
        jClass.staticVars = new Slots(jClass.staticSlotCount);
        for (JField field : jClass.fields) {
            if (field.classMember.IsStatic() && field.classMember.IsFinal()) {
                initStaticFinalVar(jClass, field);
            }
        }
    }

    //因为Go语言会保证新创建的Slot结构体有默认值(num字段是 0，ref字段是nil)，而浮点数0编码之后和整数0相同，所以不用做任 何操作就可以保证静
//态变量有默认初始值(数字类型是0，引用类型 是null)。如果静态变量属于基本类型或String类型，有final修饰符， 且它的值在编译期已知，
//则该值存储在class文件常量池中。 initStaticFinalVar()函数从常量池中加载常量值，然后给静态变量 赋值
    public void initStaticFinalVar(JClass jClass, JField field) {
        Slots vars = jClass.staticVars;
        JConstantPool cp = jClass.constantPool;
        int cpIndex = field.ConstValueIndex();
        int slotId = field.SlotId();
        if (cpIndex > 0) {
            switch (field.classMember.Descriptor()) {
                case "Z":
                case "B":
                case "C":
                case "S":
                case "I":
                    int val = (int) cp.GetConstant(cpIndex);
                    vars.SetInt(slotId, val);
                    break;
                case "J":
                    long vall = (long) cp.GetConstant(cpIndex);
                    vars.SetLong(slotId, vall);
                    break;
                case "F":
                    float valf = (float) cp.GetConstant(cpIndex);
                    vars.SetFloat(slotId, valf);
                    break;
                case "D":
                    double vald = (double) cp.GetConstant(cpIndex);
                    vars.SetDouble(slotId, vald);
                    break;
                case "Ljava/lang/String;":
                    String goStr = (String) cp.GetConstant(cpIndex);
                    JObject jStr = StringPool.JString(jClass.Loader(), goStr);
                    vars.SetRef(slotId, jStr);

            }
        }
    }


}
