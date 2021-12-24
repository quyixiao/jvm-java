package com.jvm.classfile;

import com.jvm.data.Uint16;
import com.jvm.data.Uint32;
import com.jvm.utils.AttributeInfoUtils;
import lombok.Data;

/*
Code_attribute {
    u2 attribute_name_index;
    u4 attribute_length;
    u2 max_stack;
    u2 max_locals;
    u4 code_length;
    u1 code[code_length];
    u2 exception_table_length;
    {   u2 start_pc;
        u2 end_pc;
        u2 handler_pc;
        u2 catch_type;
    } exception_table[exception_table_length];
    u2 attributes_count;
    attribute_info attributes[attributes_count];
}
*/

@Data
public class CodeAttribute implements ConstantInfo, AttributeInfo {
    private ConstantPool cp;
    private Uint16 maxStack;
    private Uint16 maxLocals;
    private byte[] code;
    private ExceptionTableEntry exceptionTable[];
    private AttributeInfo[] attributes;

    public CodeAttribute() {

    }


    public CodeAttribute(ConstantPool cp) {
        this.cp = cp;
    }

    @Override
    public void readInfo(ClassReader reader) {
        this.maxStack = reader.readUint16();
        this.maxLocals = reader.readUint16();
        Uint32 codeLength = reader.readUint32();
        this.code = reader.readBytes(codeLength.Value());
        this.exceptionTable = readExceptionTable(reader);
        this.attributes = AttributeInfoUtils.readAttributes(reader, cp);
    }

    @Override
    public Object Value() {
        return null;
    }


    public ExceptionTableEntry[] readExceptionTable(ClassReader reader) {
        Uint16 exceptionTableLength = reader.readUint16();
        ExceptionTableEntry exceptionTable[] = new ExceptionTableEntry[exceptionTableLength.Value()];
        for (int i = 0; i < exceptionTable.length; i++) {
            exceptionTable[i] = new ExceptionTableEntry(
                    reader.readUint16(),
                    reader.readUint16(),
                    reader.readUint16(),
                    reader.readUint16());
        }
        return exceptionTable;
    }


    public int MaxStack() {
        return this.maxStack.Value();
    }


    public int MaxLocals() {
        return this.maxLocals.Value();
    }

    public byte[] Code() {
        return this.code;
    }


    public ExceptionTableEntry[] ExceptionTable() {
        return this.exceptionTable;
    }


    public LineNumberTableAttribute LineNumberTableAttribute() {
        for (AttributeInfo attrInfo : this.attributes) {
            if (attrInfo instanceof LineNumberTableAttribute) {
                return (LineNumberTableAttribute) attrInfo;
            }
        }
        return null;
    }
}
