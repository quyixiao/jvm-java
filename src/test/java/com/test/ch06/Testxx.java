package com.test.ch06;

import com.jvm.classfile.ExceptionsAttribute;

public class Testxx {

    public static void test(){
        try {
            int a = 1;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {

        }
    }

    public static void main(String[] args) {
        test();
    }
}
