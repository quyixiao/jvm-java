package com.jvm.classfile;

import com.jvm.data.Uint16;

public class LocalVariableTableEntry {


    public Uint16 startPc;
    public Uint16 length;
    public Uint16 nameIndex;
    public Uint16 descriptorIndex;
    public Uint16 index;

    public LocalVariableTableEntry(Uint16 startPc, Uint16 length, Uint16 nameIndex, Uint16 descriptorIndex, Uint16 index) {
        this.startPc = startPc;
        this.length = length;
        this.nameIndex = nameIndex;
        this.descriptorIndex = descriptorIndex;
        this.index = index;
    }
}
