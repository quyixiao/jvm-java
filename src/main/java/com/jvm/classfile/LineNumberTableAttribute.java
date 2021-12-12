package com.jvm.classfile;

import com.jvm.data.Uint16;
import lombok.Data;

import java.awt.image.ImageConsumer;

/*
LineNumberTable_attribute {
    u2 attribute_name_index;
    u4 attribute_length;
    u2 line_number_table_length;
    {   u2 start_pc;
        u2 line_number;
    } line_number_table[line_number_table_length];
}
*/
@Data
public class LineNumberTableAttribute implements ConstantInfo ,AttributeInfo {

    public LineNumberTableEntry lineNumberTable[];

    @Override
    public void readInfo(ClassReader reader) {
        Uint16 lineNumberTableLength = reader.readUint16();
        this.lineNumberTable = new LineNumberTableEntry[lineNumberTableLength.Value()];
        for (int i = 0; i < this.lineNumberTable.length; i++) {
            this.lineNumberTable[i] = new LineNumberTableEntry(
                    reader.readUint16(),
                    reader.readUint16());
        }
    }
}