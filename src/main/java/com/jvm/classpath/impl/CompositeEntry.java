package com.jvm.classpath.impl;

import com.jvm.classpath.Entry;
import com.jvm.utils.FileUtils;

import java.util.ArrayList;
import java.util.List;

public class CompositeEntry implements Entry {
    private List<Entry> entries;

    public CompositeEntry() {}

    public CompositeEntry(List<Entry> entries) {
        this.entries = entries;
    }

    public CompositeEntry(String pathList) {
        entries = new ArrayList<>();
        for (String path : pathList.split(Entry.PathListSeparator)) {
            entries.add(FileUtils.newEntry(path));
        }
    }

    @Override
    public byte[] readClass(String className) {
        for (Entry entry : entries) {
            byte[] data = entry.readClass(className);
            if (data != null) {
                return data;
            }
        }
        return null;
    }

}
