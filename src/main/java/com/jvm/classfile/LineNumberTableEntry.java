package com.jvm.classfile;

import com.jvm.data.Uint16;
import lombok.Data;

@Data
public class LineNumberTableEntry {

    public Uint16 startPc;
    public Uint16 lineNumber ;

    public LineNumberTableEntry(Uint16 startPc, Uint16 lineNumber) {
        this.startPc = startPc;
        this.lineNumber = lineNumber;
    }
}
