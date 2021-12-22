package com.jvm.jnative.java.io;

import com.jvm.jnative.JNativeMethod;
import com.jvm.rtda.Frame;
import com.jvm.rtda.LocalVars;
import com.jvm.rtda.OperandStack;
import com.jvm.rtda.heap.JObject;
import com.jvm.rtda.heap.StringPool;

import java.io.File;

// public native int getBooleanAttributes0(File f);
// (Ljava/io/File;)I
public class UnFSgetBooleanAttributes0 implements JNativeMethod {
    @Override
    public void run(Frame frame) {
        LocalVars vars = frame.LocalVars();
        JObject f = vars.GetRef(1);
        String path = _getPath(f);

        // todo
        int attributes0 = 0;
        if (_exists(path)) {
            attributes0 |= 0x01;
        }
        if (_isDir(path)) {
            attributes0 |= 0x04;
        }

        OperandStack stack = frame.OperandStack();
        stack.PushInt(attributes0);
    }


    public String _getPath(JObject fileObj) {
        JObject pathStr = fileObj.GetRefVar("path", "Ljava/lang/String;");
        return StringPool.GoString(pathStr);
    }

    // http://stackoverflow.com/questions/10510691/how-to-check-whether-a-file-or-directory-denoted-by-a-path-exists-in-golang
// exists returns whether the given file or directory exists or not
    public boolean _exists(String path) {
        File file = new File(path);
        if (file.exists()) {
            return true;
        }
        return false;
    }

    // http://stackoverflow.com/questions/8824571/golang-determining-whether-file-points-to-file-or-directory
    public boolean _isDir(String path) {
        File file = new File(path);
        if (file.isDirectory()) {
            return true;
        }
        return false;
    }

}
