package com.jvm.classfile;

import com.jvm.data.Uint32;



/*
CONSTANT_Integer_info {
    u1 tag;
    u4 bytes;
}
*/
public class ConstantIntegerInfo implements ConstantInfo<Integer> {
    public Integer val;
    @Override
    public void readInfo(ClassReader reader) {
        Uint32 bytes = reader.readUint32();
        this.val = bytes.Value();
    }

    public Integer Value(){
        return this.val;
    }



}
