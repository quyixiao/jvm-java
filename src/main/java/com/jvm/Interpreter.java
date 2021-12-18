package com.jvm;

import com.jvm.instructions.Factory;
import com.jvm.instructions.base.BytecodeReader;
import com.jvm.instructions.base.Instruction;
import com.jvm.instructions.references.INVOKE_VIRTUAL;
import com.jvm.rtda.Frame;
import com.jvm.rtda.JThread;
import com.jvm.rtda.heap.JMethod;

public class Interpreter {


    //interpret()方法的其余代码先创建一个Thread实例，然后创建 一个帧并把它推入Java虚拟机栈顶，最后执行方法。
    public static void interpret(JMethod jMethod) {
        JThread thread = new JThread();
        Frame frame = thread.NewFrame(jMethod);
        thread.PushFrame(frame);
        loop(thread, jMethod.Code());
    }


    //把局部变量表和操作数栈的内容打印出来，以此来观察方法 的执行结果。还剩一个loop()函数
    public static void  loop(JThread thread, byte [] bytecode) {
        Frame frame = thread.PopFrame();
        BytecodeReader reader = new BytecodeReader();

        while(true) {
            int pc = frame.NextPC();
            thread.SetPC(pc);
            // decode
            reader.Reset(bytecode, pc);
            int opcode = reader.ReadUint8().Value();
            Instruction inst = Factory.NewInstruction(opcode);
            System.out.println(inst);
            if(inst instanceof INVOKE_VIRTUAL){
                System.out.println("--------");
            }
            inst.FetchOperands(reader);

            frame.SetNextPC(reader.PC());
            // execute
            inst.Execute(frame);
        }
    }




}
