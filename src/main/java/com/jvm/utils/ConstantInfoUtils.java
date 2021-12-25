package com.jvm.utils;

import com.jvm.classfile.*;
import com.jvm.data.Uint16;
import com.jvm.data.Uint8;


public class ConstantInfoUtils {

    public static final int CONSTANT_Class = 7;
    public static final int CONSTANT_Fieldref = 9;
    public static final int CONSTANT_Methodref = 10;
    public static final int CONSTANT_InterfaceMethodref = 11;
    public static final int CONSTANT_String = 8;
    public static final int CONSTANT_Integer = 3;
    public static final int CONSTANT_Float = 4;
    public static final int CONSTANT_Long = 5;
    public static final int CONSTANT_Double = 6;
    public static final int CONSTANT_NameAndType = 12;
    public static final int CONSTANT_Utf8 = 1;
    public static final int CONSTANT_MethodHandle = 15;
    public static final int CONSTANT_MethodType = 16;
    public static final int CONSTANT_InvokeDynamic = 18;


    public static ConstantPool readConstantPool(ClassReader reader) {
        Uint16 cpCount = reader.readUint16();

        ConstantInfo constantInfos[] = new ConstantInfo[cpCount.Value()];

        ConstantPool cp = new ConstantPool();
        cp.setConstantInfos(constantInfos);

        // The constant_pool table is indexed from 1 to constant_pool_count - 1.
        for (int i = 1; i < cpCount.Value(); i++) {
            cp.constantInfos[i] = ConstantInfoUtils.readConstantInfo(reader, cp);
            // http://docs.oracle.com/javase/specs/jvms/se8/html/jvms-4.html#jvms-4.4.5
            // All 8-byte constants take up two entries in the constant_pool table of the class file.
            // If a CONSTANT_Long_info or CONSTANT_Double_info structure is the item in the constant_pool
            // table at index n, then the next usable item in the pool is located at index n+2.
            // The constant_pool index n+1 must be valid but is considered unusable.
            if (cp.constantInfos[i] instanceof ConstantLongInfo || cp.constantInfos[i] instanceof ConstantDoubleInfo) {
                i++;
            }
        }

        return cp;
    }


    public static ConstantInfo readConstantInfo(ClassReader reader, ConstantPool cp) {
        Uint8 tag = reader.readUint8();
        ConstantInfo c = newConstantInfo(tag, cp);
        c.readInfo(reader);
        return c;
    }


    public static ConstantInfo newConstantInfo(Uint8 tag, ConstantPool cp) {
        switch (tag.Value()) {
            case CONSTANT_Integer:
                return new ConstantIntegerInfo();
            case CONSTANT_Float:
                return new ConstantFloatInfo();
            case CONSTANT_Long:
                return new ConstantLongInfo();
            case CONSTANT_Double:
                return new ConstantDoubleInfo();
            case CONSTANT_Utf8:
                return new ConstantUtf8Info();
            case CONSTANT_String:
                return new ConstantStringInfo(cp);
            case CONSTANT_Class:
                return new ConstantClassInfo(cp);
            case CONSTANT_Fieldref:
                return new ConstantFieldrefInfo(new ConstantMemberrefInfo(cp));
            case CONSTANT_Methodref:
                return new ConstantMethodrefInfo(new ConstantMemberrefInfo(cp));
            case CONSTANT_InterfaceMethodref:
                return new ConstantInterfaceMethodrefInfo(new ConstantMemberrefInfo(cp));
            case CONSTANT_NameAndType:
                return new ConstantNameAndTypeInfo();
            case CONSTANT_MethodType:
                return new ConstantMethodTypeInfo();
            case CONSTANT_MethodHandle:
                return new ConstantMethodHandleInfo();
            case CONSTANT_InvokeDynamic:
                return new ConstantInvokeDynamicInfo();
            default:
                ExceptionUtils.throwException("java.lang.ClassFormatError: constant pool tag!");
        }

        return null;

    }
}
