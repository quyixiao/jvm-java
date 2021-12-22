package com.jvm.jnative.java.lang;


//StackTraceElement结构体用来记录Java虚拟机栈帧信息
public class StackTraceElement {
    public String fileName;                                //fileName字段给出类所 在的文件名
    public String className;                            //className字段给出声明方法的类名
    public String methodName;                                //methodName字段给出方 法名
    public int lineNumber;                                    //lineNumber字段给出帧正在执行哪行代码

    public StackTraceElement(String fileName, String className, String methodName, int lineNumber) {
        this.fileName = fileName;
        this.className = className;
        this.methodName = methodName;
        this.lineNumber = lineNumber;
    }

    public String String() {
        return this.className + "." + this.methodName + "(" + this.fileName + ":" + this.lineNumber + ")";
    }
}
