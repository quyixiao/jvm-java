package com.jvm.classfile;

import com.jvm.data.Uint16;

public class ConstantNameAndTypeInfo implements ConstantInfo {

    public Uint16 nameIndex;
    public Uint16 descriptorIndex;


    @Override
    public void readInfo(ClassReader reader) {
        this.nameIndex = reader.readUint16();
        this.descriptorIndex = reader.readUint16();
    }

    @Override
    public Object Value() {
        return null;
    }
}

