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





    public ConstantValueAttribute  ConstantValueAttribute()  {
        for ( AttributeInfo attrInfo :this.attributes ){
            if(attrInfo instanceof  ConstantValueAttribute){
                return (ConstantValueAttribute)attrInfo;
            }
        }
        return null;
    }


    public byte[] RuntimeVisibleAnnotationsAttributeData() {
        return this.getUnparsedAttributeData("RuntimeVisibleAnnotations");
    }

    public byte[] RuntimeVisibleParameterAnnotationsAttributeData() {
        return this.getUnparsedAttributeData("RuntimeVisibleParameterAnnotationsAttribute");
    }

    // AnnotationDefault 属性是个长度可变的属性，它出现在某些method_info结构体的属性表里，而那种method_info结构体，
// 则用来表示注解类型中的元素，AnnotationDefault属性记录了由method_info结构所表示的那个元素的默认值，Java虚拟机默认值
// 可供取用，以便合适的反射API能够将其提供给调用者。
    public byte[] AnnotationDefaultAttributeData() {
        return this.getUnparsedAttributeData("AnnotationDefault");
    }

    public byte[] getUnparsedAttributeData(String name) {
        for (AttributeInfo attrInfo : this.attributes) {
            if (attrInfo instanceof UnparsedAttribute) {
                UnparsedAttribute unparsedAttr = (UnparsedAttribute) attrInfo;
                if (unparsedAttr.equals(name)) {
                    return unparsedAttr.info;
                }
            }
        }
        return null;
    }



    public ExceptionsAttribute ExceptionsAttribute() {
        for  (AttributeInfo  attrInfo : this.attributes){
            if(attrInfo instanceof  ExceptionsAttribute){
                return (ExceptionsAttribute) attrInfo;
            }
        }
        return null;
    }

}
