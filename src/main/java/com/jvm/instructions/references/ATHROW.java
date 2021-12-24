package com.jvm.instructions.references;

import com.alibaba.fastjson.JSON;
import com.jvm.instructions.base.NoOperandsInstruction;
import com.jvm.rtda.Frame;
import com.jvm.rtda.JThread;
import com.jvm.rtda.OperandStack;
import com.jvm.rtda.heap.JObject;
import com.jvm.rtda.heap.StringPool;
import com.jvm.utils.ExceptionUtils;
import lombok.extern.slf4j.Slf4j;

// Throw exception or error
// 在程序中显式的抛出异常的操作由athrow指令实现，除了这种情况，还有别的异常会在其他的Java虚拟机指令检测到异常状况由虚拟机自动抛出
@Slf4j
public class ATHROW extends NoOperandsInstruction {
    //athrow指令的操作数是一个异常对象引用，从操作数栈弹出。
    @Override
    public void Execute(Frame frame) {
        JObject ex = frame.OperandStack().PopRef();
        if (ex == null) {
            ExceptionUtils.throwException("java.lang.NullPointerException");
        }

        JThread thread = frame.Thread();
        if (!findAndGotoExceptionHandler(thread, ex)) {
            handleUncaughtException(thread, ex);
        }
    }

    //先从操作数栈中弹出异常对象引用，如果该引用是null，则抛 出NullPointerException异常，否则看是否可以找到并跳转到异常处 理代码。
    //从当前帧开始，遍历Java虚拟机栈，查找方法的异常处理表。
    //假设遍历到帧F，如果在F对应的方法中找不到异常处理项，则把F 弹出，继续遍历。反之如果找到了异常处理项，在跳转到异常处理 代码之前，要先把F的操作数栈清空，
    //然后把异常对象引用推入栈 顶。OperandStack结构体的Clear()方法是新增加的，后面给出它的 代码。
    public boolean findAndGotoExceptionHandler(JThread thread, JObject ex) {
        while (true) {
            Frame frame = thread.CurrentFrame();
            int pc = frame.NextPC() - 1;

            int handlerPC = frame.Method().FindExceptionHandler(ex.Class(), pc);
            if (handlerPC > 0) {
                OperandStack stack = frame.OperandStack();
                stack.Clear();
                stack.PushRef(ex);
                frame.SetNextPC(handlerPC);
                return true;
            }

            thread.PopFrame();
            if (thread.IsStackEmpty()) {
                break;
            }
        }
        return false;
    }

    // todo
    //如果遍历完Java虚拟机栈还是找不到异常处理代码，则 handleUncaughtException()函数打印出Java虚拟机栈信息
    //handleUncaughtException()函数把Java虚拟机栈清空，然后打 印出异常信息。由于Java虚拟机栈已经空了，所以解释器也就终止 执行了。
    //上面的代码使用Go语言的reflect包打印Java虚拟机栈信 息。可以猜到，异常对象的extra字段中存放的就是Java虚拟机栈信 息，那么这个extra字段是什么时候设置的呢?
    public void handleUncaughtException(JThread thread, JObject ex) {
        thread.ClearStack();
        JObject jMsg = ex.GetRefVar("detailMessage", "Ljava/lang/String;");
        String goMsg=StringPool.GoString(jMsg);
        log.info(ex.Class().JavaName() + ": " + goMsg);
        Object stes=ex.Extra();
        System.out.println(JSON.toJSONString(stes));
       /* //todo
        for (int i = 0; i < stes.Len(); i++)  {
            ste := stes.Index(i).Interface().(interface {
                String() string
            })
            println("\tat " + ste.String())
        }*/
    }

}

