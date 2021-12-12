package com.jvm.classpath.impl;

import com.jvm.classpath.Entry;
import com.jvm.utils.ClassReaderUtils;
import com.jvm.utils.StringUtils;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Enumeration;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;


public class ZipEntry implements Entry {

    public String absPath;

    public ZipEntry(String absPath) {
        this.absPath = absPath;
    }

    @Override
    public byte[] readClass(String className) {
        try {
            File file = new File(absPath);
            JarFile jar = new JarFile(file);
            Enumeration<JarEntry> en = jar.entries();
            while (en.hasMoreElements()) {
                JarEntry je = en.nextElement();
                String name = je.getName();
                if (name.endsWith(StringUtils.getClassName(className))) {
                    InputStream is = jar.getInputStream(je);
                    return ClassReaderUtils.readClass(is);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }


    @Override
    public String toString() {
        return absPath;
    }


}
