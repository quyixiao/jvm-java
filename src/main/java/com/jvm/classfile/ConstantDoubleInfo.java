package com.jvm.classfile;

import com.jvm.utils.ByteUtil;
import lombok.Data;


/*
CONSTANT_Double_info {
    u1 tag;
    u4 high_bytes;
    u4 low_bytes;
}
*/


@Data
public class ConstantDoubleInfo implements ConstantInfo {
    public Double val;

    @Override
    public void readInfo(ClassReader reader) {
        byte bytes[] = reader.readBytes(8);
        this.val = ByteUtil.getDouble(bytes);
    }


    public Double Value() {
        return this.val;
    }
}






