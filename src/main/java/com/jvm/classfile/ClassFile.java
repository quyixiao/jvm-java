package com.jvm.classfile;

import com.jvm.data.Uint16;
import com.jvm.data.Uint32;
import com.jvm.utils.AttributeInfoUtils;
import com.jvm.utils.ConstantInfoUtils;
import com.jvm.utils.ExceptionUtils;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;


/*
ClassFile {
    u4             magic;
    u2             minor_version;
    u2             major_version;
    u2             constant_pool_count;
    cp_info        constant_pool[constant_pool_count-1];
    u2             access_flags;
    u2             this_class;
    u2             super_class;
    u2             interfaces_count;
    u2             interfaces[interfaces_count];
    u2             fields_count;
    field_info     fields[fields_count];
    u2             methods_count;
    method_info    methods[methods_count];
    u2             attributes_count;
    attribute_info attributes[attributes_count];
}
*/
@Slf4j
@Data
public class ClassFile {

    public Uint32 magic;
    public Uint16 minorVersion;
    public Uint16 majorVersion;
    public ConstantPool constantPool;
    public Uint16 accessFlags;
    public Uint16 thisClass;
    public Uint16 superClass;
    public Uint16[] interfaces;
    public MemberInfo[] fields;
    public MemberInfo[] methods;
    public AttributeInfo[] attributes;


    public static ClassFile Parse(byte[] classData) {
        ClassReader cr = new ClassReader(classData);
        ClassFile cf = new ClassFile();
        cf.read(cr);
        return cf;
    }

    public void read(ClassReader reader) {
        this.readAndCheckMagic(reader);
        this.readAndCheckVersion(reader);
        this.constantPool = ConstantInfoUtils.readConstantPool(reader);
        this.accessFlags = reader.readUint16();
        this.thisClass = reader.readUint16();
        this.superClass = reader.readUint16();
        this.interfaces = reader.readUint16s();
        this.fields = AttributeInfoUtils.readMembers(reader, this.constantPool);
        this.methods = AttributeInfoUtils.readMembers(reader, this.constantPool);
        this.attributes = AttributeInfoUtils.readAttributes(reader, this.constantPool);
    }


    public String ClassName() {
        return this.constantPool.getClassName(this.thisClass);
    }

    public String SuperClassName() {
        if (this.superClass.Value() > 0) {
            return this.constantPool.getClassName(this.superClass);
        }
        return "";
    }

    public String[] InterfaceNames() {
        String[] interfaceNames = new String[(this.interfaces.length)];
        for (int i = 0; i < this.interfaces.length; i++) {
            interfaceNames[i] = this.constantPool.getClassName(this.interfaces[i]);
        }
        return interfaceNames;
    }

    public void readAndCheckMagic(ClassReader reader) {
        magic = reader.readUint32();
        if (magic.Value() != 0xCAFEBABE) {
            ExceptionUtils.throwException("魔数不正确 ");
        }
    }


    public void readAndCheckVersion(ClassReader reader) {
        this.minorVersion = reader.readUint16();
        this.majorVersion = reader.readUint16();
        switch (this.majorVersion.Value()) {
            case 45:
                return;
            case 46:
            case 47:
            case 48:
            case 49:
            case 50:
            case 51:
            case 52:
                if (this.minorVersion.Value() == 0) {
                    return;
                }

        }
        ExceptionUtils.throwException("java.lang.UnsupportedClassVersionError!");
    }

    public Uint16 MinorVersion() {
        return this.minorVersion;
    }

    public Uint16 MajorVersion() {
        return this.majorVersion;
    }

    public ConstantPool ConstantPool() {
        return this.constantPool;
    }

    public Uint16 AccessFlags() {
        return this.accessFlags;
    }

    public MemberInfo[] Fields() {
        return this.fields;
    }

    public MemberInfo[] Methods() {
        return this.methods;
    }






   public SourceFileAttribute  SourceFileAttribute() {
        for  (AttributeInfo attrInfo :this.attributes ){
            if(attrInfo instanceof SourceFileAttribute ){
                return (SourceFileAttribute) attrInfo;
            }
        }
        return null;
    }

}
