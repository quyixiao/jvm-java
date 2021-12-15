package com.jvm.rtda.heap;

import com.jvm.classfile.*;

public class JConstantPool {


    public JClass jClass;
    public Object consts[];

    public JConstantPool(JClass jClass, ConstantPool cp) {
        int cpCount = cp.constantInfos.length;
        this.consts = new Object[cpCount];
        this.jClass = jClass;
        ConstantInfo[] cfCp = cp.constantInfos;
        for (int i = 1; i < cpCount; i++) {
            ConstantInfo cpInfo = cfCp[i];
            if (cpInfo instanceof ConstantIntegerInfo) {
                ConstantIntegerInfo intInfo = (ConstantIntegerInfo) cpInfo;
                consts[i] = intInfo.Value();
            } else if (cpInfo instanceof ConstantFloatInfo) {
                ConstantFloatInfo intInfo = (ConstantFloatInfo) cpInfo;
                consts[i] = intInfo.Value();
            } else if (cpInfo instanceof ConstantLongInfo) { //如果是long或double型常量，也是直接提取常量值放进consts 中。但是要注意，这两种类型的常量在常量池中都是占据两个位 置，所以索引要特殊处理
                ConstantLongInfo longInfo = (ConstantLongInfo) cpInfo;
                consts[i] = longInfo.Value();
                i++;
            } else if (cpInfo instanceof ConstantDoubleInfo) {
                ConstantDoubleInfo doubleInfo = (ConstantDoubleInfo) cpInfo;
                consts[i] = doubleInfo.Value();
                i++;
            } else if (cpInfo instanceof ConstantStringInfo) {//如果是字符串常量，直接取出Go语言字符串，放进consts中，
                ConstantStringInfo stringInfo = (ConstantStringInfo) cpInfo;
                consts[i] = stringInfo.String();
            } else if (cpInfo instanceof ConstantClassInfo) {//还剩下4种类型的常量需要处理，分别是类、字段、方法和接口 方法的符号引用
                ConstantClassInfo classInfo = (ConstantClassInfo) cpInfo;
                consts[i] = new ClassRef(this, classInfo);
            } else if (cpInfo instanceof ConstantFieldrefInfo) {
                ConstantFieldrefInfo fieldrefInfo = (ConstantFieldrefInfo) cpInfo;
                consts[i] = new FieldRef(this, fieldrefInfo);
            } else if (cpInfo instanceof ConstantMethodrefInfo) {
                ConstantMethodrefInfo methodrefInfo = (ConstantMethodrefInfo) cpInfo;
                consts[i] = new MethodRef(this, methodrefInfo);
            } else if (cpInfo instanceof ConstantInterfaceMethodrefInfo) {
                ConstantInterfaceMethodrefInfo methodrefInfo = (ConstantInterfaceMethodrefInfo) cpInfo;
                consts[i] = new InterfaceMethodRef(this, methodrefInfo);
            } else {

            }
        }
    }

    public Object GetConstant(int index) {
        return this.consts[index];
    }


}
