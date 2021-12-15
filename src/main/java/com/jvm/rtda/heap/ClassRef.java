package com.jvm.rtda.heap;

import com.jvm.classfile.ConstantClassInfo;

public class ClassRef {

    public SymRef symRef;

    //ClassRef继承了SymRef，但是并没有添加任何字段。
//newClassRef()函数根据class文件中存储的类常量创建ClassRef实 例
    public ClassRef(JConstantPool cp, ConstantClassInfo classInfo) {
        this.symRef = new SymRef();
        this.symRef.cp = cp;
        this.symRef.className = classInfo.Name();
    }

}
