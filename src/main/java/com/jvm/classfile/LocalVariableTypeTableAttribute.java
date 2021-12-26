package com.jvm.classfile;

import com.jvm.data.Uint16;
import lombok.Data;

/*
LocalVariableTypeTable_attribute {
    u2 attribute_name_index;
    u4 attribute_length;
    u2 local_variable_type_table_length;
    {   u2 start_pc;
        u2 length;
        u2 name_index;
        u2 signature_index;
        u2 index;
    } local_variable_type_table[local_variable_type_table_length];
}
*/
@Data
public class LocalVariableTypeTableAttribute implements AttributeInfo {


    public LocalVariableTypeTableEntry localVariableTypeTable[];

    @Override
    public void readInfo(ClassReader reader) {
        Uint16 localVariableTypeTableLength = reader.readUint16();
        this.localVariableTypeTable = new LocalVariableTypeTableEntry[localVariableTypeTableLength.Value()];
        for (int i = 0; i < this.localVariableTypeTable.length; i++) {
            this.localVariableTypeTable[i] = new LocalVariableTypeTableEntry(
                    reader.readUint16(),
                    reader.readUint16(),
                    reader.readUint16(),
                    reader.readUint16(),
                    reader.readUint16()
            );
        }
    }

}
