package com.jvm;

import com.jvm.classpath.Classpath;
import com.jvm.instructions.base.ClassInitLogic;
import com.jvm.rtda.Frame;
import com.jvm.rtda.JThread;
import com.jvm.rtda.heap.*;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class JVM {

    public Cmd cmd;

    public JClassLoader classLoader;

    public JThread mainThread;


    // newJVM()函数创建JVM结构体实例
    public JVM(Cmd cmd) {
        Classpath cp = new Classpath(cmd.getXjreOption(), cmd.getCpOption());
        this.cmd = cmd;
        this.classLoader = new JClassLoader(cp);
        this.mainThread = new JThread();
    }


    //start()方法先初始化VM类，然后执行主类的main()方法
    public void start() {
        this.initVM();
        this.execMain();
    }

    //initVM()先加载sun.mis.VM类，然后执行其类初始化方法
    public void initVM() {
        JClass vmClass = this.classLoader.LoadClass("sun/misc/VM");
        ClassInitLogic.InitClass(this.mainThread, vmClass);
        Interpreter.interpret(this.mainThread, this.cmd.verboseInstFlag);
    }

    //execMain()方法先加载主类，然后执行其main()方法
    public void execMain() {
        String className = this.cmd.jclass.replaceAll(".", "/");
        JClass mainClass = this.classLoader.LoadClass(className);
        JMethod mainMethod = mainClass.GetMainMethod();
        if (mainMethod == null) {
            log.info("Main method not found in class {} ", this.cmd.jclass);
            return;
        }
        //在调 用main()方法之前，需要给它传递args参数，这是通过直接操作局 部变量表实现的。
        //createArgsArray()方法把Go的[]string变量转换成 Java的字符串数组，代码是从interpreter.go文件中拷贝过来的
        JObject argsArr = this.createArgsArray();
        Frame frame = this.mainThread.NewFrame(mainMethod);
        frame.LocalVars().SetRef(0, argsArr);
        this.mainThread.PushFrame(frame);
        Interpreter.interpret(this.mainThread, this.cmd.verboseInstFlag);
    }

    public JObject createArgsArray() {
        JClass stringClass = this.classLoader.LoadClass("java/lang/String");
        int argsLen = this.cmd.args.length;
        JObject argsArr = stringClass.ArrayClass().NewArray(argsLen);
        JObject jArgs[] = argsArr.Refs();
        for (int i = 0; i < this.cmd.args.length; i++) {
            String arg = this.cmd.args[i];
            jArgs[i] = StringPool.JString(this.classLoader, arg);
        }
        return argsArr;
    }


}
