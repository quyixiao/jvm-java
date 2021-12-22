package com.jvm.jnative.java.lang.jsystem;

import com.jvm.instructions.base.MethodInvokeLogic;
import com.jvm.jnative.JNativeMethod;
import com.jvm.jnative.java.lang.ClassHelper;
import com.jvm.rtda.Frame;
import com.jvm.rtda.JThread;
import com.jvm.rtda.LocalVars;
import com.jvm.rtda.OperandStack;
import com.jvm.rtda.heap.JMethod;
import com.jvm.rtda.heap.JObject;
import com.jvm.rtda.heap.StringPool;

import java.util.HashMap;
import java.util.Map;

// private static native Properties initProperties(Properties props);
// (Ljava/util/Properties;)Ljava/util/Properties;
public class SystemInitProperties implements JNativeMethod {
    @Override
    public void run(Frame frame) {
        LocalVars vars = frame.LocalVars();
        JObject props = vars.GetRef(0);

        OperandStack stack = frame.OperandStack();
        stack.PushRef(props);

        // public synchronized Object setProperty(String key, String value)
        JMethod setPropMethod = props.Class().GetInstanceMethod("setProperty", "(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/Object;");
        JThread thread = frame.Thread();
        for (Map.Entry<String, String> entry : _sysProps().entrySet()) {
            String key = entry.getKey();
            String val = entry.getValue();
            JObject jKey = StringPool.JString(frame.Method().classMember.Class().Loader(), key);
            JObject jVal = StringPool.JString(frame.Method().classMember.Class().Loader(), val);
            OperandStack ops = new OperandStack(3);
            ops.PushRef(props);
            ops.PushRef(jKey);
            ops.PushRef(jVal);
            Frame shimFrame = ClassHelper.NewShimFrame(thread, ops);
            thread.PushFrame(shimFrame);

            MethodInvokeLogic.InvokeMethod(shimFrame, setPropMethod);
        }
    }


    public Map<String, String> _sysProps() {
        Map<String, String> map = new HashMap<>();

        map.put("java.version", "1.8.0");
        map.put("java.vendor", "jvm.go");
        map.put("java.vendor.url", "https://github.com/zxh0/jvm.go");
        map.put("java.home", "todo");
        map.put("java.class.version", "52.0");
        map.put("java.class.path", "todo");
        map.put("java.awt.graphicsenv", "sun.awt.CGraphicsEnvironment");
        map.put("os.name", "");   // todo
        map.put("os.arch", ""); // todo
        map.put("os.version", "");             // todo
        map.put("file.separator", "/");            // todo os.PathSeparator
        map.put("path.separator", ":");            // todo os.PathListSeparator
        map.put("line.separator", "\n");           // todo
        map.put("user.name", "");             // todo
        map.put("user.home", "");             // todo
        map.put("user.dir", ".");            // todo
        map.put("user.country", "CN");           // todo
        map.put("file.encoding", "UTF-8");
        map.put("sun.stdout.encoding", "UTF-8");
        map.put("sun.stderr.encoding", "UTF-8");
        return map;
    }
}
