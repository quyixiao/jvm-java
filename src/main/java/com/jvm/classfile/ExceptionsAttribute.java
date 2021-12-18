package com.jvm.classfile;

import com.jvm.data.Uint16;
import lombok.Data;

/*
Exceptions_attribute {
    u2 attribute_name_index;
    u4 attribute_length;
    u2 number_of_exceptions;
    u2 exception_index_table[number_of_exceptions];
}
*/
@Data
public class ExceptionsAttribute implements ConstantInfo,AttributeInfo {

    Uint16 exceptionIndexTable[];

    @Override
    public void readInfo(ClassReader reader) {
        this.exceptionIndexTable = reader.readUint16s();
    }




    public Uint16 [] ExceptionIndexTable() {
        return this.exceptionIndexTable;
    }


    @Override
    public Object Value() {
        return null;
    }


}
