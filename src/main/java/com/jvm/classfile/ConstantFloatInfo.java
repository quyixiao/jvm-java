package com.jvm.classfile;

import com.jvm.data.Uint32;
import com.jvm.utils.ByteUtil;
import lombok.Data;
import lombok.val;

/*
CONSTANT_Float_info {
    u1 tag;
    u4 bytes;
}
*/
@Data
public class ConstantFloatInfo implements ConstantInfo{

    public  Float val;


    public ConstantFloatInfo(Float val) {
        this.val = val;
    }

    @Override
    public void readInfo(ClassReader reader) {
        byte[] bytes = reader.readBytes(4);
        this.val = ByteUtil.getFloat(bytes);
    }


}
