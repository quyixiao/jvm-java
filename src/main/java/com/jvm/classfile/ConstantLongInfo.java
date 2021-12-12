package com.jvm.classfile;

import com.jvm.data.Uint64;

/*
CONSTANT_Long_info {
    u1 tag;
    u4 high_bytes;
    u4 low_bytes;
}
*/
public class ConstantLongInfo implements ConstantInfo {

    public Long val;

    @Override
    public void readInfo(ClassReader reader) {
        Uint64 uint64 = reader.readUint64();
        this.val = uint64.Value();
    }


    public Long Value() {
        return this.val;
    }
}
