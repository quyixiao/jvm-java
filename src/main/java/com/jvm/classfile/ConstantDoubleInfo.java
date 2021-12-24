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
        byte byte2[] = new byte[8];
        for(int i = 0 ;i < 8 ;i ++){
            byte2[i] = bytes[7-i];
        }
        this.val = ByteUtil.getDouble(byte2);
    }


    public Double Value() {
        return this.val;
    }
}






