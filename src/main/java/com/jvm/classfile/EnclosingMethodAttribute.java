package com.jvm.classfile;

/*
EnclosingMethod_attribute {
    u2 attribute_name_index;
    u4 attribute_length;
    u2 class_index;
    u2 method_index;
}
*/

import com.jvm.data.Uint16;
import com.jvm.utils.t.Tuple2;
import lombok.Data;

@Data
public class EnclosingMethodAttribute implements ConstantInfo {
    public ConstantPool cp;
    public Uint16 classIndex;
    public Uint16 methodIndex;

    @Override
    public void readInfo(ClassReader reader) {
        this.classIndex = reader.readUint16();
        this.methodIndex = reader.readUint16();
    }


    public String ClassName() {
        return this.cp.getClassName(this.classIndex);
    }

    public Tuple2<String, String> MethodNameAndDescriptor() {
        if (this.methodIndex.Value() > 0) {
            return this.cp.getNameAndType(this.methodIndex);
        } else {
            return new Tuple2<>("", "");
        }
    }

    @Override
    public Object Value() {
        return null;
    }



}
