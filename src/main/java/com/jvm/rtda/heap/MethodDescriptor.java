package com.jvm.rtda.heap;

import java.util.ArrayList;
import java.util.List;

public class MethodDescriptor {

    public List<String> parameterTypes;
    public String returnType;

    public void addParameterType(String t) {
        if (this.parameterTypes == null) {
            this.parameterTypes = new ArrayList<>();
        }
        this.parameterTypes.add(t);
    }


}
