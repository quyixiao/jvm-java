package com.jvm.classfile;


import com.jvm.data.Uint16;
import lombok.Data;

/*
CONSTANT_InvokeDynamic_info {
    u1 tag;
    u2 bootstrap_method_attr_index;
    u2 name_and_type_index;
}
*/
@Data
public class ConstantInvokeDynamicInfo implements ConstantInfo {


    public Uint16 bootstrapMethodAttrIndex;
    public Uint16 nameAndTypeIndex;


    @Override
    public void readInfo(ClassReader reader) {
        this.bootstrapMethodAttrIndex = reader.readUint16();
        this.nameAndTypeIndex = reader.readUint16();
    }

    @Override
    public Object Value() {
        return null;
    }
}
