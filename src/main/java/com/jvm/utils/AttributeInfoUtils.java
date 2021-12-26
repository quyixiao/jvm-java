package com.jvm.utils;

import com.jvm.classfile.*;
import com.jvm.data.Uint16;
import com.jvm.data.Uint32;

public class AttributeInfoUtils {


    // read field or method table
    public static MemberInfo[] readMembers(ClassReader reader, ConstantPool cp) {
        Uint16 memberCount = reader.readUint16();
        MemberInfo members[] = new MemberInfo[memberCount.Value()];
        for (int i = 0; i < members.length; i++) {
            members[i] = readMember(reader, cp);
        }
        return members;
    }

    public static MemberInfo readMember(ClassReader reader, ConstantPool cp) {
        return new MemberInfo(
                cp,
                reader.readUint16(),
                reader.readUint16(),
                reader.readUint16(),
                readAttributes(reader, cp)
        );
    }


    public static AttributeInfo[] readAttributes(ClassReader reader, ConstantPool cp) {
        Uint16 attributesCount = reader.readUint16();
        AttributeInfo[] attributes = new AttributeInfo[attributesCount.Value()];
        for (int i = 0; i < attributes.length; i++) {
            attributes[i] = readAttribute(reader, cp);
        }
        return attributes;
    }

    public static AttributeInfo readAttribute(ClassReader reader, ConstantPool cp) {
        Uint16 attrNameIndex = reader.readUint16();
        String attrName = cp.getUtf8(attrNameIndex);
        Uint32 attrLen = reader.readUint32();
        AttributeInfo attrInfo = newAttributeInfo(attrName, attrLen, cp);
        attrInfo.readInfo(reader);
        return attrInfo;
    }

    public static AttributeInfo newAttributeInfo(String attrName, Uint32 attrLen, ConstantPool cp) {
        switch (attrName) {
            case "BootstrapMethods":
                return new BootstrapMethodsAttribute();
            case "Code":
                return new CodeAttribute(cp);
            case "ConstantValue":
                return new ConstantValueAttribute();
            case "Deprecated":
                return new DeprecatedAttribute();
            case "EnclosingMethod":
                return new EnclosingMethodAttribute( cp);
            case "Exceptions":
                return new ExceptionsAttribute();
            case "InnerClasses":
                return new InnerClassesAttribute();
            case "LineNumberTable":
                return new LineNumberTableAttribute();
            case "LocalVariableTable":
                return new LocalVariableTableAttribute();
            case "LocalVariableTypeTable":
                return new LocalVariableTypeTableAttribute();
            case "SourceFile":
                return new SourceFileAttribute(cp);
            case "Synthetic":
                return new SyntheticAttribute();
            default:
                return new UnparsedAttribute(attrName, attrLen, null);
        }
    }


}
