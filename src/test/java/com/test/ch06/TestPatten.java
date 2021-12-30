package com.test.ch06;

public class TestPatten {

    public static void main(String[] args) {
        String content = "/xxb/getLocationConfig";

        String pattern = "/yjh/*";


        System.out.println(match(pattern,content));
    }

    public static boolean match(String pattern, String url) {
        try {
            char patterns[] = pattern.toCharArray();
            char urls[] = url.toCharArray();
            int length = patterns.length > urls.length ? urls.length : patterns.length;
            for (int i = 0; i < length; i++) {
                if (patterns[i] == '*') {
                    return true;
                }
                if (patterns[i] != urls[i]) {
                    return false;
                }
            }
            return false;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}
