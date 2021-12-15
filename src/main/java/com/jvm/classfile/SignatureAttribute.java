package com.jvm.classfile;

import com.jvm.data.Uint16;
import lombok.Data;

/*
Signature_attribute {
    u2 attribute_name_index;
    u4 attribute_length;
    u2 signature_index;
}
*/
@Data
public class SignatureAttribute implements ConstantInfo {

    public ConstantPool cp;
    public Uint16 signatureIndex;


    @Override
    public void readInfo(ClassReader reader) {
        this.signatureIndex = reader.readUint16();
    }

    @Override
    public Object Value() {
        return null;
    }


    public String  Signature()  {
        return this.cp.getUtf8(this.signatureIndex);
    }

}


