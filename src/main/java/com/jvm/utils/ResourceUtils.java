package com.jvm.utils;

import lombok.extern.slf4j.Slf4j;

import java.net.URL;

@Slf4j
public class ResourceUtils {

    public static final String CLASSPATH_URL_PREFIX = "classpath:";


    public static final String FILE_URL_PREFIX = "file:";


    public static final String JAR_URL_PREFIX = "jar:";

    public static final String URL_PROTOCOL_FILE = "file";

    public static final String URL_PROTOCOL_JAR = "jar";


    public static final String URL_PROTOCOL_ZIP = "zip";


    public static final String URL_PROTOCOL_WSJAR = "wsjar";


    public static final String URL_PROTOCOL_VFSZIP = "vfszip";


    public static final String URL_PROTOCOL_VFSFILE = "vfsfile";


    public static final String URL_PROTOCOL_VFS = "vfs";


    public static final String JAR_FILE_EXTENSION = ".jar";


    public static final String JAR_URL_SEPARATOR = "!/";


    public static boolean isJarURL(URL url) {
        String protocol = url.getProtocol();
        return (URL_PROTOCOL_JAR.equals(protocol) || URL_PROTOCOL_ZIP.equals(protocol) ||
                URL_PROTOCOL_VFSZIP.equals(protocol) || URL_PROTOCOL_WSJAR.equals(protocol));
    }


}
