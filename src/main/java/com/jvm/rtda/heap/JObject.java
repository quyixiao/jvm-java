package com.jvm.rtda.heap;

public class JObject {
    public JClass jClass;    //一个存放对象的Class指针
    public Slots fields;


    public JObject(JClass jClass) {
        this.jClass = jClass;
        this.fields = new Slots(jClass.instanceSlotCount);
    }


    // getters
    public JClass Class() {
        return this.jClass;
    }

    public Slots Fields() {
        return this.fields;
    }

    public boolean IsInstanceOf(JClass jClass) {
        return jClass.isAssignableFrom(this.jClass);
    }


}
