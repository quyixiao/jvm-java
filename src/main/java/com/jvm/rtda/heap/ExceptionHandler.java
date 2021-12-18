package com.jvm.rtda.heap;

public class ExceptionHandler {

    public  int startPc   ;
    public  int endPc     ;
    public  int handlerPc ;
    public  ClassRef catchType ;


    public ExceptionHandler(int startPc, int endPc, int handlerPc, ClassRef catchType) {
        this.startPc = startPc;
        this.endPc = endPc;
        this.handlerPc = handlerPc;
        this.catchType = catchType;
    }






}
