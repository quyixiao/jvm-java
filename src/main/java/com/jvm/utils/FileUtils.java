package com.jvm.utils;

import com.jvm.classpath.Entry;
import com.jvm.classpath.impl.CompositeEntry;
import com.jvm.classpath.impl.DirEntry;
import com.jvm.classpath.impl.ZipEntry;
import com.sun.xml.internal.messaging.saaj.util.FinalArrayList;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class FileUtils {

    public static List<String> ergodic(File file, List<String> resultFileName) {
        File[] files = file.listFiles();
        if (files == null)
            return resultFileName;// 判断目录下是不是空的
        for (File f : files) {
            if (f.isDirectory()) {// 判断是否文件夹
                ergodic(f, resultFileName);// 调用自身,查找子目录
            } else
                resultFileName.add(f.getPath());
        }
        return resultFileName;
    }

    public static Entry newEntry(String path) {
        if (path.contains(":")) {
            return new CompositeEntry(path);
        }
        if (path.endsWith("*")) {
            return newWildcardEntry(path);
        }
        if (path.endsWith(".jar") || path.endsWith(".JAR") || path.endsWith(".zip") || path.endsWith(".ZIP")) {
            return new ZipEntry(path);
        }
        return new DirEntry(path);
    }


    public static CompositeEntry newWildcardEntry(String path ){
        String baseDir  = path.substring(0,path.length()-1) ;// remove *
        List<Entry> entries = new ArrayList<>();
        List<String> fileNameList = new ArrayList<>();
        File file = new File(baseDir);
        ergodic(file,fileNameList);
        for(String fileName :fileNameList){
            File f = new File(fileName);
            if(f.isDirectory() && !fileName.equals(baseDir)){
                continue;
            }
            if(fileName.endsWith(".jar") || fileName.endsWith(".JAR")){
                entries.add(new ZipEntry(fileName));
            }
        }
        return new CompositeEntry(entries);
    }

}
