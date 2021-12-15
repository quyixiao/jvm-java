package com.jvm.classfile;

import com.jvm.data.Uint16;
import com.jvm.utils.t.Tuple2;
import lombok.Data;

@Data
public class ConstantMemberrefInfo implements ConstantInfo {

    public ConstantPool cp;
    public Uint16 classIndex;
    public Uint16 nameAndTypeIndex;


    public ConstantMemberrefInfo(ConstantPool cp) {
        this.cp = cp;
    }

    @Override
    public void readInfo(ClassReader reader) {
        this.classIndex = reader.readUint16();
        this.nameAndTypeIndex = reader.readUint16();
    }

    @Override
    public Object Value() {
        return null;
    }

    public String ClassName() {
        return this.cp.getClassName(this.classIndex);
    }


    public Tuple2<String, String> NameAndDescriptor() {
        return this.cp.getNameAndType(this.nameAndTypeIndex);
    }

}
