package com.jvm.classfile;

import com.jvm.data.Uint16;

/*
CONSTANT_MethodType_info {
    u1 tag;
    u2 descriptor_index;
}
*/
public class ConstantMethodTypeInfo implements ConstantInfo {

    public Uint16 descriptorIndex;


    @Override
    public void readInfo(ClassReader reader) {
        this.descriptorIndex = reader.readUint16();
    }

    @Override
    public Object Value() {
        return null;
    }
}
