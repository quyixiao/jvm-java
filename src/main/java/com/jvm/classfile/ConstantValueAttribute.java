package com.jvm.classfile;

import com.jvm.data.Uint16;
import lombok.Data;

import java.awt.image.ImageConsumer;

/*
ConstantValue_attribute {
    u2 attribute_name_index;
    u4 attribute_length;
    u2 constantvalue_index;
}
*/
@Data
public class ConstantValueAttribute implements ConstantInfo , AttributeInfo {
    public Uint16 constantValueIndex;


    @Override
    public void readInfo(ClassReader reader) {
        this.constantValueIndex = reader.readUint16();
    }


}
