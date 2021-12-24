package com.jvm.classfile;

import com.jvm.data.Uint32;
import lombok.Data;

/*
attribute_info {
    u2 attribute_name_index;
    u4 attribute_length;
    u1 info[attribute_length];
}
*/

@Data
public class UnparsedAttribute implements ConstantInfo, AttributeInfo {
    public String name;
    public Uint32 length;
    public byte[] info;

    public UnparsedAttribute(String name, Uint32 length, byte[] info) {
        this.name = name;
        this.length = length;
        this.info = info;
    }

    @Override
    public void readInfo(ClassReader reader) {
        this.info = reader.readBytes(this.length.Value());
    }

    @Override
    public Object Value() {
        return null;
    }

}
