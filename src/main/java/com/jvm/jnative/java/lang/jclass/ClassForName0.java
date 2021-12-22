package com.jvm.jnative.java.lang.jclass;

import com.jvm.instructions.base.ClassInitLogic;
import com.jvm.jnative.JNativeMethod;
import com.jvm.rtda.Frame;
import com.jvm.rtda.JThread;
import com.jvm.rtda.LocalVars;
import com.jvm.rtda.OperandStack;
import com.jvm.rtda.heap.JClass;
import com.jvm.rtda.heap.JObject;
import com.jvm.rtda.heap.StringPool;


// private static native Class<?> forName0(String name, boolean initialize,
//                                         ClassLoader loader,
//                                         Class<?> caller)
//     throws ClassNotFoundException;
// (Ljava/lang/String;ZLjava/lang/ClassLoader;Ljava/lang/Class;)Ljava/lang/Class;
public class ClassForName0 implements JNativeMethod {
    @Override
    public void run(Frame frame) {
        LocalVars vars = frame.LocalVars();
        JObject jName = vars.GetRef(0);
        boolean initialize = vars.GetBoolean(1);
        String goName = StringPool.GoString(jName);
        goName = goName.replaceAll(".", "/");
        JClass goClass = frame.Method().classMember.Class().Loader().LoadClass(goName);
        JObject jClass = goClass.JObject();
        if (initialize && !goClass.InitStarted()) {
            // undo forName0
            JThread thread = frame.Thread();
            frame.SetNextPC(thread.PC());
            // init class
            ClassInitLogic.InitClass(thread, goClass);
        } else {
            OperandStack stack = frame.OperandStack();
            stack.PushRef(jClass);
        }
    }
}
