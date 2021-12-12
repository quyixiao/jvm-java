package com.jvm.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringUtils {
    private static Pattern numberPattern = Pattern.compile("[0-9]*");

    public static boolean hasLength(CharSequence str) {
        return (str != null && str.length() > 0);
    }


    public static boolean hasLength(String str) {
        return hasLength((CharSequence) str);
    }

    public static String replace(String inString, String oldPattern, String newPattern) {
        if (!hasLength(inString) || !hasLength(oldPattern) || newPattern == null) {
            return inString;
        }
        StringBuilder sb = new StringBuilder();
        int pos = 0; // our position in the old string
        int index = inString.indexOf(oldPattern);
        // the index of an occurrence we've found, or -1
        int patLen = oldPattern.length();
        while (index >= 0) {
            sb.append(inString.substring(pos, index));
            sb.append(newPattern);
            pos = index + patLen;
            index = inString.indexOf(oldPattern, pos);
        }
        sb.append(inString.substring(pos));
        // remember to append any characters to the right of a match
        return sb.toString();
    }

    public static boolean isNotBlank(final CharSequence cs) {
        return !isBlank(cs);
    }
    public static boolean isBlank(final CharSequence cs) {
        int strLen;
        if (cs == null || (strLen = cs.length()) == 0) {
            return true;
        }
        for (int i = 0; i < strLen; i++) {
            if (!Character.isWhitespace(cs.charAt(i))) {
                return false;
            }
        }
        return true;
    }



    public static String getClassName(String className){
        return className.replaceAll("\\.", "/") + ".class";
    }


    public static void main(String[] args) {
        System.out.println(getClassName("java.lang.String"));
    }



    public static boolean isNumeric(String str) {

        Matcher isNum = numberPattern.matcher(str);
        if (!isNum.matches()) {
            return false;
        }
        return true;
    }

}
