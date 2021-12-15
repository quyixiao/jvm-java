package com.jvm.classfile;

import lombok.Data;

@Data
public class MarkerAttribute implements ConstantInfo {
    @Override
    public void readInfo(ClassReader reader) {

    }

    @Override
    public Object Value() {
        return null;
    }
}
