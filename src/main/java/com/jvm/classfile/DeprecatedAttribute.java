package com.jvm.classfile;


import lombok.Data;

/*
Deprecated_attribute {
    u2 attribute_name_index;
    u4 attribute_length;
}
*/
@Data
public class DeprecatedAttribute implements AttributeInfo {
    public MarkerAttribute markerAttribute;

    @Override
    public void readInfo(ClassReader reader) {

    }
}
