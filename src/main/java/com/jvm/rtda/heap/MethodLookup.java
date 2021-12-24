package com.jvm.rtda.heap;

public class MethodLookup {


    public static JMethod LookupMethodInClass(JClass jClass, String name, String descriptor) {
        JClass c = jClass;
        if(c == null){
            return null;
        }
        while (true) {
            if(c.methods !=null){
                for (JMethod method : c.methods) {
                    if (method.classMember.name.equals(name) && method.classMember.descriptor.equals(descriptor)) {
                        return method;
                    }
                }
            }
            c = c.superClass;
            if (c == null) {
                return null;
            }
        }
    }

    public static JMethod lookupMethodInInterfaces(JClass[] ifaces, String name, String descriptor) {
        if (ifaces != null) {
            for (JClass iface : ifaces) {
                if (iface.methods != null) {
                    for (JMethod method : iface.methods) {
                        if (method.classMember.name.equals(name) && method.classMember.descriptor.equals(descriptor)) {
                            return method;
                        }
                    }
                }
                JMethod method = lookupMethodInInterfaces(iface.interfaces, name, descriptor);
                if (method != null) {
                    return method;
                }
            }
        }
        return null;
    }

}
