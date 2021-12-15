package com.jvm.classfile;


import com.jvm.data.Uint16;
import com.jvm.data.Uint8;
import lombok.Data;

/*
CONSTANT_MethodHandle_info {
    u1 tag;
    u1 reference_kind;
    u2 reference_index;
}
*/
@Data
public class ConstantMethodHandleInfo implements  ConstantInfo {
    public Uint8 referenceKind;
    public Uint16 referenceIndex;

    @Override
    public void readInfo(ClassReader reader) {
        this.referenceKind = reader.readUint8();
        this.referenceIndex = reader.readUint16();
    }

    @Override
    public Object Value() {
        return null;
    }


}
