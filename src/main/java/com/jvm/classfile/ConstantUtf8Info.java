package com.jvm.classfile;

import com.jvm.data.Uint16;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.io.UnsupportedEncodingException;

/*
CONSTANT_Utf8_info {
    u1 tag;
    u2 length;
    u1 bytes[length];
}
*/
@Data
@Slf4j
public class ConstantUtf8Info implements ConstantInfo {

    public String str;


    @Override
    public void readInfo(ClassReader reader) {
        Uint16 length = reader.readUint16();
        byte bytes[] = reader.readBytes(length.Value());
        try {
            this.str = new String(bytes, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            log.error("异常", e);
        }
    }

    @Override
    public Object Value() {
        return null;
    }


    public String Str() {
        return this.str;
    }


}
