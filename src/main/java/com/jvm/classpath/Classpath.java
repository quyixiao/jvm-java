package com.jvm.classpath;

import com.jvm.utils.FileUtils;
import com.jvm.utils.Filepath;
import com.jvm.utils.StringUtils;

import java.io.File;

public class Classpath {
    private Entry bootClasspath;
    private Entry extClasspath;
    private Entry userClasspath;


    public Classpath(String jreOption, String cpOption) {
        parseBootAndExtClasspath(jreOption);
        parseUserClasspath(cpOption);
    }

    public void parseBootAndExtClasspath(String jreOption) {
        String jreDir = getJreDir(jreOption);
        // jre/lib/*
        String jreLibPath = Filepath.join(jreDir, "lib", "*");
        bootClasspath = FileUtils.newWildcardEntry(jreLibPath);

        // jre/lib/ext/*
        String jreExtPath = Filepath.join(jreDir, "lib", "ext", "*");
        extClasspath = FileUtils.newWildcardEntry(jreExtPath);
    }

    public void parseUserClasspath(String cpOption) {
        if (cpOption == "") {
            cpOption = ".";
        }
        userClasspath = FileUtils.newEntry(cpOption);
    }

    private String getJreDir(String jreOption) {
        if (jreOption != "" && exists(jreOption)) {
            return jreOption;
        }
        if (exists("./jre")) {
            return "./jre";
        }
        String javaHome = System.getenv("JAVA_HOME");
        if (StringUtils.isNotBlank(javaHome)) {
            return Filepath.join(javaHome, "jre");
        }
        return null;
    }


    public boolean exists(String path) {
        File file = new File(path);
        return file.exists();
    }

    public byte[] readClass(String className) {
        byte[] data = bootClasspath.readClass(className);
        if (data != null) {
            return data;
        }
        data = extClasspath.readClass(className);
        if (data != null) {
            return data;
        }
        return userClasspath.readClass(className);
    }
}
