package com.jvm.classfile;


import java.security.PublicKey;

/*
cp_info {
    u1 tag;
    u1 info[];
}
*/
public interface ConstantInfo {


    void readInfo(ClassReader reader);





}
