package com.jvm.classfile;

import com.jvm.data.Uint16;
import lombok.Data;

@Data
public class LocalVariableTypeTableEntry {
    private Uint16 startPc;
    private Uint16 length;
    private Uint16 nameIndex;
    private Uint16 signatureIndex;
    private Uint16 index;

    public LocalVariableTypeTableEntry(Uint16 startPc, Uint16 length, Uint16 nameIndex, Uint16 signatureIndex, Uint16 index) {
        this.startPc = startPc;
        this.length = length;
        this.nameIndex = nameIndex;
        this.signatureIndex = signatureIndex;
        this.index = index;
    }
}
