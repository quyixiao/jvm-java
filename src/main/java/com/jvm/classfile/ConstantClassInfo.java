package com.jvm.classfile;


import com.jvm.data.Uint16;
import lombok.Data;

/*
CONSTANT_Class_info {
    u1 tag;
    u2 name_index;
}
*/
@Data
public class ConstantClassInfo implements ConstantInfo {
    public ConstantPool cp;
    public Uint16 nameIndex;


    public ConstantClassInfo(ConstantPool cp) {
        this.cp = cp;
    }

    @Override
    public void readInfo(ClassReader reader) {
        this.nameIndex = reader.readUint16();
    }

    @Override
    public Object Value() {
        return null;
    }

    public String Name() {
        return this.cp.getUtf8(this.nameIndex);
    }

}
