package com.jvm.jnative.java.lang;

import com.jvm.jnative.JNativeMethod;
import com.jvm.rtda.Frame;
import com.jvm.rtda.JThread;
import com.jvm.rtda.heap.JClass;
import com.jvm.rtda.heap.JMethod;
import com.jvm.rtda.heap.JObject;

// private native Throwable fillInStackTrace(int dummy);
// (I)Ljava/lang/Throwable;
public class ThrowableFillInStackTrace implements JNativeMethod {
    @Override
    public void run(Frame frame) {
        JObject thiz = frame.LocalVars().GetThis();
        frame.OperandStack().PushRef(thiz);

        StackTraceElement[]  stes = createStackTraceElements(thiz, frame.Thread());
        thiz.SetExtra(stes);
    }


    //这个函数需要解释一下。由于栈顶两帧正在执行 fillInStackTrace(int)和fillInStackTrace()方法，所以需要跳过这两 帧。
//这两帧下面的几帧正在执行异常类的构造函数，所以也要跳 过，具体要跳过多少帧数则要看异常类的继承层次。
    public StackTraceElement[] createStackTraceElements(JObject tObj, JThread thread) {
        //distanceToObject()函数计算所需跳过的帧数
        int skip=distanceToObject(tObj.Class()) + 2;
        //计算好需要跳过的帧之后，调用Thread结构体的GetFrames() 方法拿到完整的Java虚拟机栈，然后reslice一下就是真正需要的帧。
        //GetFrames()方法只是调用了Stack结构体的getFrames()方法
        Frame temp[] = thread.GetFrames();
        Frame frames [ ] = new Frame[temp.length - skip];
        System.arraycopy(temp, skip, frames,0 ,frames.length );
        StackTraceElement stes[]=new StackTraceElement[frames.length];
        for (int i = 0 ;i <  frames.length ; i ++) {
            //createStackTraceElement()函数根据帧创建StackTraceElement实
            stes[i] = createStackTraceElement(frames[i]);
        }
        return stes;
    }

    public int distanceToObject(JClass jClass) {
        int distance = 0;
        JClass c = null;
        while (true) {
            c = jClass.SuperClass();
            if (c == null) {
                break;
            }
            distance++;
        }
        return distance;
    }

    public StackTraceElement createStackTraceElement(Frame frame) {
        JMethod method = frame.Method();
        JClass jClass = method.classMember.Class();
        return new StackTraceElement(
                jClass.SourceFile(),
                jClass.JavaName(),
                method.classMember.Name(),
                method.GetLineNumber(frame.NextPC() - 1));
    }

}
