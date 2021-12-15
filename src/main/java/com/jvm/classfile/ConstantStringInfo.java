package com.jvm.classfile;


import com.jvm.data.Uint16;

/*
CONSTANT_String_info {
    u1 tag;
    u2 string_index;
}
*/
public class ConstantStringInfo implements ConstantInfo {


    public ConstantPool cp;
    public Uint16 stringIndex;


    public ConstantStringInfo(ConstantPool cp) {
        this.cp = cp;
    }

    @Override
    public void readInfo(ClassReader reader) {
        this.stringIndex = reader.readUint16();
    }

    @Override
    public Object Value() {
        return null;
    }


    public String String() {
        return this.cp.getUtf8(this.stringIndex);
    }
}
