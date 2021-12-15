package com.jvm.rtda.heap;

import com.jvm.classfile.ConstantMemberrefInfo;
import com.jvm.utils.t.Tuple2;

public class MemberRef {

    public SymRef symRef;
    public String name;            //在Java中，我们并不能在同一个类中定义 名字相同，但类型不同的两个字段，那么字段符号引用为什么还要 存放字段描述符呢?答案是，
    // 这只是Java语言的限制，而不是Java 虚拟机规范的限制。也就是说，站在Java虚拟机的角度，一个类是 完全可以有多个同名字段的，只要它们的类型互不相同就可以。
    public String descriptor;


    //copyMemberRefInfo()方法从class文件内存储的字段或方法常量中 提取数据
    public void copyMemberRefInfo(ConstantMemberrefInfo refInfo) {
        this.symRef.className = refInfo.ClassName();
        Tuple2<String, String> data = refInfo.NameAndDescriptor();
        this.name = data.getFirst();
        this.descriptor = data.getSecond();
    }

    public String Name() {
        return this.name;
    }

    public String Descriptor() {
        return this.descriptor;
    }


}
