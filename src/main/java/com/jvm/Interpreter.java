package com.jvm;

import com.jvm.instructions.Factory;
import com.jvm.instructions.base.BytecodeReader;
import com.jvm.instructions.base.Instruction;
import com.jvm.rtda.Frame;
import com.jvm.rtda.JThread;
import com.jvm.rtda.heap.JMethod;
import lombok.extern.slf4j.Slf4j;


@Slf4j
public class Interpreter {


    public static  int line  = 1;

    //interpret()方法的其余代码先创建一个Thread实例，然后创建 一个帧并把它推入Java虚拟机栈顶，最后执行方法。
    public static void interpret(JThread thread, boolean logInst) {
        try {
            loop(thread, logInst);
        } catch (Exception e) {
            e.printStackTrace();
            logFrames(thread);
        }
    }


    //把局部变量表和操作数栈的内容打印出来，以此来观察方法 的执行结果。还剩一个loop()函数
    public static void loop(JThread thread, boolean logInst) {
        BytecodeReader reader = new BytecodeReader();
        while (true) {
            Frame frame = thread.CurrentFrame();
            int pc = frame.NextPC();
            thread.SetPC(pc);
            // decode
            reader.Reset(frame.Method().Code(), pc);
            int opcode = reader.ReadUint8().Value();
            Instruction inst = Factory.NewInstruction(opcode);
            inst.FetchOperands(reader);
            frame.SetNextPC(reader.PC());
            if(true ){
                logInstruction(frame ,inst);
            }

            if(line > 39829){
            //    System.out.println("bbbbbbbbbbbbb");
            }
            // execute
            inst.Execute(frame);
            if (thread.IsStackEmpty()) {
                break;
            }
        }
    }

    public  static  String getClassMethodName(Frame frame){
        JMethod method = frame.Method();
        String className = method.classMember.Class().Name();
        String methodName = method.classMember.Name();
        return className + "." + methodName;
    }

    public static void logInstruction(Frame frame, Instruction inst) {
        JMethod method = frame.Method();
        String className = method.classMember.Class().Name();
        String methodName = method.classMember.Name();
        int pc = frame.Thread().PC();
        System.out.println(line +"  "+ className + "." + methodName + "() #" + pc + " " +inst);
        line ++;
    }

    public static void logFrames(JThread thread) {
        while (!thread.IsStackEmpty()) {
            Frame frame = thread.PopFrame();
            JMethod method = frame.Method();
            String className = method.classMember.Class().Name();
            int lineNum = method.GetLineNumber(frame.NextPC());
            log.info(" line:{} pc:{} {}.{}{} \n",
                    lineNum, frame.NextPC(), className, method.classMember.Name(), method.classMember.Descriptor());
        }
    }


}
