package com.jvm.classfile;

import com.jvm.data.Uint16;
import lombok.Data;

@Data
public class InnerClassInfo  {
    public Uint16 innerClassInfoIndex;
    public Uint16 outerClassInfoIndex;
    public Uint16 innerNameIndex;
    public Uint16 innerClassAccessFlags;

    public InnerClassInfo(Uint16 innerClassInfoIndex, Uint16 outerClassInfoIndex, Uint16 innerNameIndex, Uint16 innerClassAccessFlags) {
        this.innerClassInfoIndex = innerClassInfoIndex;
        this.outerClassInfoIndex = outerClassInfoIndex;
        this.innerNameIndex = innerNameIndex;
        this.innerClassAccessFlags = innerClassAccessFlags;
    }
}
