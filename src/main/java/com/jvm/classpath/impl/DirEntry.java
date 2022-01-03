package com.jvm.classpath.impl;

import com.jvm.classpath.Entry;
import com.jvm.utils.ClassReaderUtils;
import com.jvm.utils.Filepath;
import com.jvm.utils.StringUtils;

public class DirEntry implements Entry {
    private String absDir;

    public DirEntry(String absDir) {
        this.absDir = absDir;
    }

    @Override
    public byte[] readClass(String className) {
        if(className.startsWith("com/test")){
            String a[] = className.split("/");
            className = a[a.length-1];
        }
        String classPath = Filepath.join(absDir, StringUtils.getClassName(className));
        return ClassReaderUtils.readClass(classPath);
    }

    @Override
    public String toString() {
        return absDir;
    }


}
