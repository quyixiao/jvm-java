package com.jvm.instructions.base;

import com.jvm.rtda.Frame;
import com.jvm.rtda.JThread;
import com.jvm.rtda.Slot;
import com.jvm.rtda.heap.JMethod;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class MethodInvokeLogic {


    public static void InvokeMethod(Frame invokerFrame, JMethod method) {
        JThread thread = invokerFrame.Thread();
        Frame newFrame = thread.NewFrame(method);
        thread.PushFrame(newFrame);

        //第一，long和double类型的参数要占用两个位置。
        int argSlotCount = method.ArgSlotCount();
        if (argSlotCount > 0) {
            for (int i = argSlotCount - 1; i >= 0; i--) {
                //第二，对于实例方法，Java编译器会在参数列表的 前面添加一个参数，这个隐藏的参数就是this引用。假设实际的参
                //数占据n个位置，依次把这n个变量从调用者的操作数栈中弹出，放 进被调用方法的局部变量表中，参数传递就完成了。
                Slot slot = invokerFrame.OperandStack().PopSlot();
                //注意，在代码中，并没有对long和double类型做特别处理。因为 操作的是Slot结构体，所以这是没问题的。
                newFrame.LocalVars().SetSlot(i, slot);
            }
        }

    }

}
