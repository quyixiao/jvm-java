package com.jvm.rtda.heap;

public class MethodLookup {


    public static JMethod LookupMethodInClass(JClass jClass, String name, String descriptor) {
        JClass c = jClass;
        while (true) {
            for (JMethod method : c.methods) {
                if (method.classMember.name.equals(name) && method.classMember.descriptor.equals(descriptor)) {
                    return method;
                }
            }
            c = jClass.superClass;
            if (c == null) {
                return null;
            }
        }
    }

    public static JMethod lookupMethodInInterfaces(JClass[] ifaces, String name, String descriptor) {
        for (JClass iface : ifaces) {
            for (JMethod method : iface.methods) {
                if (method.classMember.name.equals(name) && method.classMember.descriptor.equals(descriptor)) {
                    return method;
                }
            }
            JMethod method = lookupMethodInInterfaces(iface.interfaces, name, descriptor);
            if (method != null) {
                return method;
            }
        }

        return null;
    }

}
