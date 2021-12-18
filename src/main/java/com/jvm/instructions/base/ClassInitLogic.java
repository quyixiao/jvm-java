package com.jvm.instructions.base;

import com.jvm.rtda.Frame;
import com.jvm.rtda.JThread;
import com.jvm.rtda.heap.JClass;
import com.jvm.rtda.heap.JMethod;

public class ClassInitLogic {

    // jvms 5.5
    public static void  InitClass(JThread thread, JClass jClass) {
        //nitClass()函数先调用StartInit()方法把类的initStarted状态设 置成true以免进入死循环
        jClass.StartInit();
        //然后调用scheduleClinit()函数准备执行 类的初始化方法
        scheduleClinit(thread, jClass);
        initSuperClass(thread, jClass);
    }

    public static void  scheduleClinit(JThread thread, JClass jClass) {
        //类初始化方法没有参数，所以不需要传递参数。
        JMethod clinit = jClass.GetClinitMethod();
        if (clinit != null)  {
            // exec <clinit>
            Frame newFrame = thread.NewFrame(clinit);
            thread.PushFrame(newFrame);
        }
    }

    public static void  initSuperClass(JThread thread, JClass jClass) {
        //注意，这里有意使用了scheduleClinit这个函数名而非 invokeClinit，因为有可能要先执行超类的初始化方法，
        if (!jClass.IsInterface()) {
            JClass superClass = jClass.SuperClass();
            if (superClass != null && !superClass.InitStarted() ){
                //如果超类的初始化还没有开始，就递归调用InitClass()函数执 行超类的初始化方法，这样可以保证超类的初始化方法对应的帧在
                //子类上面，使超类初始化方法先于子类执行。
                InitClass(thread, superClass);
            }
        }
    }



}
