package com.jvm.classpath;

public interface Entry {

    String PathListSeparator = ":"; // OS-specific path list separator

    byte[] readClass(String className);

}
