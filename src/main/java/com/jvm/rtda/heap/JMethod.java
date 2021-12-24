package com.jvm.rtda.heap;

import com.jvm.classfile.CodeAttribute;
import com.jvm.classfile.ExceptionsAttribute;
import com.jvm.classfile.LineNumberTableAttribute;
import com.jvm.classfile.MemberInfo;
import com.jvm.data.Uint16;

import java.util.List;

public class JMethod {
    public ClassMember classMember;
    public int maxStack;                    // maxStack和maxLocals字段分别存放操作数栈和局部变量表大 小，这两个值是由Java编译器计算好的。
    public int maxLocals;
    public byte[] code;
    public int argSlotCount;

    public ExceptionTable exceptionTable;           // todo: rename
    public LineNumberTableAttribute lineNumberTable;
    public ExceptionsAttribute exceptions; // todo: rename
    public byte[] parameterAnnotationData;                         // RuntimeVisibleParameterAnnotations_attribute
    public byte[] annotationDefaultData;// AnnotationDefault_attribute
    public MethodDescriptor parsedDescriptor;

    public JMethod() {
        if (this.classMember == null) {
            this.classMember = new ClassMember();
        }
    }


    public JMethod(ClassMember classMember, byte[] code) {
        this.classMember = classMember;
        this.code = code;
    }

    public void copyAttributes(MemberInfo cfMethod) {
        CodeAttribute codeAttr = cfMethod.CodeAttribute();
        if (codeAttr != null) {
            this.maxStack = codeAttr.MaxStack();
            this.maxLocals = codeAttr.MaxLocals();
            this.code = codeAttr.Code();
            this.lineNumberTable = codeAttr.LineNumberTableAttribute();
            this.exceptionTable = new ExceptionTable(codeAttr.ExceptionTable(), this.classMember.Class().constantPool);
        }

        this.exceptions = cfMethod.ExceptionsAttribute();
        this.classMember.annotationData = cfMethod.RuntimeVisibleAnnotationsAttributeData();
        this.parameterAnnotationData = cfMethod.RuntimeVisibleParameterAnnotationsAttributeData();
        this.annotationDefaultData = cfMethod.AnnotationDefaultAttributeData();
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

    //ArgSlotCount()只是个Getter方法而已
    public int ArgSlotCount() {
        return this.argSlotCount;
    }

    public void calcArgSlotCount() {
        MethodDescriptorParser parser = new MethodDescriptorParser();
        MethodDescriptor parsedDescriptor = parser.parseMethodDescriptor(this.classMember.descriptor);
        for (String paramType : parsedDescriptor.parameterTypes) {
            this.argSlotCount++;
            if (paramType == "J" || paramType == "D") {
                this.argSlotCount++;
            }
        }
        if (!this.classMember.IsStatic()) {
            this.argSlotCount++; // `this` reference
        }
    }


    public void injectCodeAttribute(String returnType) {
        this.maxStack = 4; // 因为本地方法帧的 局部变量表只用来存放参数值，所以把argSlotCount赋给maxLocals 字段刚好。
        this.maxLocals = this.argSlotCount;
        //至于code字段，也就是本地方法的字节码，第一条指令 都是0xFE，第二条指令则根据函数的返回值选择相应的返回指令。
        switch (returnType.toCharArray()[0]) {
            case 'V':
                this.code = new byte[]{(byte) 0xfe, (byte) 0xb1}; // return    0xfe = 15 * 16 + 14
                break;
            case 'L':
            case '[':
                this.code = new byte[]{(byte) 0xfe, (byte) 0xb0}; // areturn
                break;
            case 'D':
                this.code = new byte[]{(byte) 0xfe, (byte) 0xaf}; // dreturn
                break;
            case 'F':
                this.code = new byte[]{(byte) 0xfe, (byte) 0xae};// freturn
                break;
            case 'J':
                this.code = new byte[]{(byte) 0xfe, (byte) 0xad}; // lreturn
                break;
            default:
                this.code = new byte[]{(byte) 0xfe, (byte) 0xac};// ireturn
                break;
        }
    }


    public byte[] ParameterAnnotationData() {
        return this.parameterAnnotationData;
    }


    public byte[] AnnotationDefaultData() {
        return this.annotationDefaultData;
    }

    public MethodDescriptor ParsedDescriptor() {
        return this.parsedDescriptor;
    }


    //FindExceptionHandler()方法调用 ExceptionTable.findExceptionHandler()方法搜索异常处理表，如果 能够找到对应的异常处理项，
    //则返回它的handlerPc字段，否则返 回–1
    public int FindExceptionHandler(JClass exClass, int pc) {
        ExceptionHandler handler = this.exceptionTable.findExceptionHandler(exClass, pc);
        if (handler != null) {
            return handler.handlerPc;
        }
        return -1;
    }


    public int GetLineNumber(int pc) {
        if (this.IsNative()) {
            return -2;
        }
        if (this.lineNumberTable == null) {
            return -1;
        }
        return this.lineNumberTable.GetLineNumber(pc);
    }

    public boolean isConstructor() {
        return !this.classMember.IsStatic() && this.classMember.name.equals("<init>");
    }


    public boolean isClinit() {
        return this.classMember.IsStatic() && (this.classMember.name.equals("<clinit>"));
    }

    // reflection
    public JClass[] ParameterTypes() {
        if (this.argSlotCount == 0) {
            return null;
        }

        List<String> paramTypes = this.parsedDescriptor.parameterTypes;
        JClass[] paramClasses = new JClass[paramTypes.size()];
        for (int i = 0; i < paramTypes.size(); i++) {
            String paramType = paramTypes.get(i);
            String paramClassName = ClassNameHelper.toClassName(paramType);
            paramClasses[i] = this.classMember.jClass.loader.LoadClass(paramClassName);
        }

        return paramClasses;
    }

    public JClass ReturnType() {
        String returnType = this.parsedDescriptor.returnType;
        String returnClassName = ClassNameHelper.toClassName(returnType);
        return this.classMember.jClass.loader.LoadClass(returnClassName);
    }

    public JClass[] ExceptionTypes() {
        if (this.exceptions == null) {
            return null;
        }

        Uint16[] exIndexTable = this.exceptions.ExceptionIndexTable();
        JClass exClasses[] = new JClass[exIndexTable.length];
        JConstantPool cp = this.classMember.jClass.constantPool;
        for (int i = 0; i < exIndexTable.length; i++) {
            Uint16 exIndex = exIndexTable[i];
            ClassRef classRef = (ClassRef) cp.GetConstant(exIndex.Value());
            exClasses[i] = classRef.symRef.ResolvedClass();
        }
        return exClasses;
    }




}
