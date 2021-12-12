package com.jvm.classfile;

import com.jvm.data.Uint16;

public class BootstrapMethod {
    public Uint16 bootstrapMethodRef;
    public Uint16[] bootstrapArguments;

    public BootstrapMethod(Uint16 bootstrapMethodRef, Uint16[] bootstrapArguments) {
        this.bootstrapMethodRef = bootstrapMethodRef;
        this.bootstrapArguments = bootstrapArguments;
    }
}
