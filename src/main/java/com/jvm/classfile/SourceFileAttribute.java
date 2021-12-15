package com.jvm.classfile;

import com.jvm.data.Uint16;
import lombok.Data;

@Data
public class SourceFileAttribute implements ConstantInfo,AttributeInfo {
    private ConstantPool cp;
    private Uint16 sourceFileIndex;

    public SourceFileAttribute() {
    }

    public SourceFileAttribute(ConstantPool cp) {
        this.cp = cp;
    }

    @Override
    public void readInfo(ClassReader reader) {
        this.sourceFileIndex = reader.readUint16();
    }

    @Override
    public Object Value() {
        return null;
    }


    public String FileName() {
        return this.cp.getUtf8(this.sourceFileIndex);
    }


}
