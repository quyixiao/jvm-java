package com.jvm.jnative.java.io;


import com.jvm.jnative.JNativeMethod;
import com.jvm.rtda.Frame;
import com.jvm.rtda.LocalVars;
import com.jvm.rtda.OperandStack;
import com.jvm.rtda.heap.JObject;
import com.jvm.rtda.heap.StringPool;

public class UnFSCanonicalize0 implements JNativeMethod {

    @Override
    public void run(Frame frame) {
      LocalVars  vars = frame.LocalVars();
        JObject path = vars.GetRef(1);

        String goPath = StringPool.GoString(path);
        String goPath2 = Clean(goPath);
        if (!goPath2.equals(goPath)  ){
            path = StringPool.JString(frame.Method().classMember.Class().Loader(), goPath2);
        }
        OperandStack stack = frame.OperandStack();
        stack.PushRef(path);
    }



    public String Clean(String path) {
        String originalPathStr = path;
        char[] originalPath = path.toCharArray();
        int volLen = 0;
        path = path.substring(volLen);
        if (path.equals("")) {
            if (volLen > 1 && originalPath[1] != ':') {
                // should be UNC
                return FromSlash(charToString(originalPath));
            }
            return  charToString(originalPath)+ ".";
        }
        boolean rooted = IsPathSeparator(originalPath[0]);

        int n = path.length();

        Lazybuf out = new Lazybuf(path, originalPathStr, volLen);
        int r = 0;
        int dotdot = 0;
        if (rooted) {
            out.append( Separator.toCharArray()[0]);
            r = 1;
                    dotdot = 1;
        }

        while (r < n) {
            char b []= path.toCharArray();
            if (IsPathSeparator(b[r])) {
                // empty path element
                r++;
            } else if (b[r] == '.' && (r + 1 == n || IsPathSeparator(b[r + 1]))) {
                // . element
                r++;

            } else if (b[r] == '.' && b[r + 1] == '.' && (r + 2 == n || IsPathSeparator(b[r + 2]))) {
                // .. element: remove to last separator
                r += 2;
                if (out.w > dotdot) {
                    // can backtrack
                    out.w--;
                    while (out.w > dotdot && !IsPathSeparator(out.index(out.w))) {
                        out.w--;
                    }
                } else if (!rooted) {
                    // cannot backtrack, but not rooted, so append .. element.
                    if (out.w > 0) {
                        out.append(Separator.toCharArray()[0]);
                    }
                    out.append('.');
                    out.append('.');
                    dotdot = out.w;
                }
            }else {
                if (rooted && out.w != 1 || !rooted && out.w != 0 ){
                    out.append(Separator.toCharArray()[0]);
                }
                // copy element
                for (;r<n &&!IsPathSeparator(b[r]);r++ ){
                    out.append(b[r]);
                }
            }
        }

        // Turn empty string into "."
        if (out.w == 0 ){
            out.append('.');
        }
        return FromSlash(out.string());
    }


    public final static String Separator = "/";// OS-specific path separator
    public final static String PathListSeparator = ":"; // OS-specific path list separator

    public String FromSlash(String path) {
        if (Separator.equals("/")) {
            return path;
        }
        return path.replaceAll("/", new String(Separator));
    }


    public String charToString(char[] chars) {
        StringBuilder sb = new StringBuilder();
        for (char c : chars) {
            sb.append(c);
        }
        return sb.toString();
    }


    public boolean IsPathSeparator(char c) {
        return Separator.equals(c + "");
    }


    public static void main(String[] args) {

        UnFSCanonicalize0 unFSCanonicalize0 = new UnFSCanonicalize0();
        System.out.println(unFSCanonicalize0.Clean("GFG/../Geeks"));
        System.out.println(unFSCanonicalize0.Clean("..GFG/./../Geeks"));
        System.out.println(unFSCanonicalize0.Clean("gfg/../../../Geek/GFG"));
        System.out.println(unFSCanonicalize0.Clean(""));
        System.out.println(unFSCanonicalize0.Clean("."));
        System.out.println(unFSCanonicalize0.Clean("///"));
        System.out.println(unFSCanonicalize0.Clean("/.//"));
        System.out.println(unFSCanonicalize0.Clean("/./"));
        System.out.println(unFSCanonicalize0.Clean(":/"));
    }

}
