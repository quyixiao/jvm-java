package com.jvm.classfile;


import com.jvm.data.Uint16;
import lombok.Data;


/*
field_info {
    u2             access_flags;
    u2             name_index;
    u2             descriptor_index;
    u2             attributes_count;
    attribute_info attributes[attributes_count];
}
method_info {
    u2             access_flags;
    u2             name_index;
    u2             descriptor_index;
    u2             attributes_count;
    attribute_info attributes[attributes_count];
}
*/


@Data
public class MemberInfo {

    public ConstantPool cp;
    public Uint16 accessFlags;
    public Uint16 nameIndex;
    public Uint16 descriptorIndex;
    public AttributeInfo[] attributes;


    public MemberInfo(ConstantPool cp, Uint16 accessFlags, Uint16 nameIndex, Uint16 descriptorIndex, AttributeInfo[] attributes) {
        this.cp = cp;
        this.accessFlags = accessFlags;
        this.nameIndex = nameIndex;
        this.descriptorIndex = descriptorIndex;
        this.attributes = attributes;
    }


    public Uint16 AccessFlags()  {
        return this.accessFlags;
    }


    public String  Name()  {
        return this.cp.getUtf8(this.nameIndex);
    }


    public String  Descriptor()  {
        return this.cp.getUtf8(this.descriptorIndex);
    }


    public CodeAttribute CodeAttribute() {
        for(AttributeInfo attributeInfo : this.attributes){
            if(attributeInfo instanceof CodeAttribute){
                return( CodeAttribute) attributeInfo;
            }
        }
        return null;
    }


}
