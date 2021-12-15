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
        this.val = ByteUtil.getFloat(bytes);
    }

    @Override
    public Float Value() {
        return this.val;
    }


}
