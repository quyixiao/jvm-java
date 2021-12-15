package com.jvm.classfile;

import com.jvm.data.Uint16;
import com.jvm.utils.ConstantInfoUtils;
import com.jvm.utils.ExceptionUtils;
import com.jvm.utils.t.Tuple2;
import lombok.Data;

@Data
public class ConstantPool implements ConstantInfo {

    public  ConstantInfo[] constantInfos;



    public ConstantInfo getConstantInfo(Uint16 index) {
        ConstantInfo cpInfo = this.constantInfos[index.Value()];
        if (cpInfo != null) {
            return cpInfo;
        }
        ExceptionUtils.throwException("Invalid constant pool index:" + index.Value() + " ! ");
        return null;
    }


    public String getClassName(Uint16 index) {
        ConstantClassInfo classInfo = (ConstantClassInfo)this.getConstantInfo(index);
        return this.getUtf8(classInfo.nameIndex);
    }

    public Tuple2<String, String> getNameAndType(Uint16 index) {
        ConstantNameAndTypeInfo ntInfo = (ConstantNameAndTypeInfo) this.getConstantInfo(index);
        String name = this.getUtf8(ntInfo.nameIndex);
        String _type = this.getUtf8(ntInfo.descriptorIndex);
        return new Tuple2<>(name, _type);
    }

    public String getUtf8(Uint16 index) {
        ConstantUtf8Info utf8Info = (ConstantUtf8Info)this.getConstantInfo(index);
        return utf8Info.str;
    }

    @Override
    public void readInfo(ClassReader reader) {

    }

    @Override
    public Object Value() {
        return null;
    }
}
