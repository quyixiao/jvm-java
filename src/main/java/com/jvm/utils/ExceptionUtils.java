package com.jvm.utils;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ExceptionUtils {

    public static void throwException(String msg){
        log.error("异常",msg);
        int a = 1 ;
        int b = 0 ;
        int c = a / b ;
    }
}
