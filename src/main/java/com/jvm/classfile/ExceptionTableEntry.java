package com.jvm.classfile;

import com.jvm.data.Uint16;
import lombok.Data;

@Data
public class ExceptionTableEntry {

    public Uint16 startPc;
    public Uint16 endPc;
    public Uint16 handlerPc;
    public Uint16 catchType;

    public ExceptionTableEntry(Uint16 startPc, Uint16 endPc, Uint16 handlerPc, Uint16 catchType) {
        this.startPc = startPc;
        this.endPc = endPc;
        this.handlerPc = handlerPc;
        this.catchType = catchType;
    }
}
