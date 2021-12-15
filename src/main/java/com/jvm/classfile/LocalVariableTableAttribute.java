package com.jvm.classfile;

import com.jvm.data.Uint16;
import lombok.Data;

@Data
public class LocalVariableTableAttribute implements ConstantInfo,AttributeInfo {

    public LocalVariableTableEntry localVariableTable[];

    @Override
    public void readInfo(ClassReader reader) {

        Uint16 localVariableTableLength = reader.readUint16();
        this.localVariableTable = new LocalVariableTableEntry[localVariableTableLength.Value()];
        for (int i = 0; i < this.localVariableTable.length; i++) {
            this.localVariableTable[i] = new LocalVariableTableEntry(
                    reader.readUint16(),
                    reader.readUint16(),
                    reader.readUint16(),
                    reader.readUint16(),
                    reader.readUint16()
            );
        }

    }

    @Override
    public Object Value() {
        return null;
    }
}
