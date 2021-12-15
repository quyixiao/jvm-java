package com.jvm.classfile;


import lombok.Data;

/*
CONSTANT_Fieldref_info {
    u1 tag;
    u2 class_index;
    u2 name_and_type_index;
}
 */
@Data
public class ConstantFieldrefInfo implements ConstantInfo {

    public ConstantMemberrefInfo constantMemberrefInfo;

    public ConstantFieldrefInfo(ConstantMemberrefInfo constantMemberrefInfo) {
        this.constantMemberrefInfo = constantMemberrefInfo;
    }

    @Override
    public void readInfo(ClassReader reader) {
        this.constantMemberrefInfo.readInfo(reader);
    }

    @Override
    public Object Value() {
        return null;
    }
}
