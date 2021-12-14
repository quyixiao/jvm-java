package com.jvm;

import com.jvm.classfile.CodeAttribute;
import com.jvm.classfile.MemberInfo;
import com.jvm.instructions.Factory;
import com.jvm.instructions.base.BytecodeReader;
import com.jvm.instructions.base.Instruction;
import com.jvm.rtda.Frame;
import com.jvm.rtda.Thread;

public class Interpreter {


    //interpret()方法的其余代码先创建一个Thread实例，然后创建 一个帧并把它推入Java虚拟机栈顶，最后执行方法。
    public static void interpret(MemberInfo methodInfo) {
        CodeAttribute codeAttr = methodInfo.CodeAttribute();        //得到Code属性之后，可以进一步获得执行方法所需的局部变 量表和操作数栈空间
        int maxLocals = codeAttr.MaxLocals();
        int maxStack = codeAttr.MaxStack();
        byte bytecode[] = codeAttr.Code();

        Thread thread = new Thread();
        Frame frame = thread.NewFrame(maxLocals, maxStack);
        thread.PushFrame(frame);

        loop(thread, bytecode);
    }


    //把局部变量表和操作数栈的内容打印出来，以此来观察方法 的执行结果。还剩一个loop()函数
    public static void  loop(Thread thread,  byte [] bytecode) {
        Frame frame = thread.PopFrame();
        BytecodeReader reader = new BytecodeReader();

        while(true) {
            int pc = frame.NextPC();
            thread.SetPC(pc);
            // decode
            reader.Reset(bytecode, pc);
            int opcode = reader.ReadUint8().Value();
            Instruction inst = Factory.NewInstruction(opcode);
            inst.FetchOperands(reader);
            frame.SetNextPC(reader.PC());
            // execute
            inst.Execute(frame);
        }
    }


    public static void  logInstruction(Frame frame, Instruction inst) {

    }


}
