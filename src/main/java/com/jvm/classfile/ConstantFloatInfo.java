package com.jvm.classfile;

import com.jvm.utils.ByteUtil;
import lombok.Data;

/*
CONSTANT_Float_info {
    u1 tag;
    u4 bytes;
}
*/
@Data
public class ConstantFloatInfo implements ConstantInfo<Float> {

    public Float val;

    @Override
    public void readInfo(ClassReader reader) {
        byte[] bytes = reader.readBytes(4);
        byte[] byte2 = new byte[4];
        for (int i = 0; i < 4; i++) {
            byte2[i] = bytes[3 - i];
        }
        this.val = ByteUtil.getFloat(byte2);
    }

    @Override
    public Float Value() {
        return this.val;
    }


}
