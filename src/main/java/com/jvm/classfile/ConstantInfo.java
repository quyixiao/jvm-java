package com.jvm.classfile;


import java.security.PublicKey;

/*
cp_info {
    u1 tag;
    u1 info[];
}
*/
public interface ConstantInfo <T> {


    void readInfo(ClassReader reader);


    T Value();




}
