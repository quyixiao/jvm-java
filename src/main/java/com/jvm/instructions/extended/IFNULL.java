package com.jvm.instructions.extended;

import com.jvm.instructions.base.Base;
import com.jvm.instructions.base.BranchInstruction;
import com.jvm.rtda.Frame;

// Branch if reference is null
public class IFNULL extends BranchInstruction {


    //根据引用是否是null进行跳转，ifnull和ifnonnull指令把栈顶的 引用弹出。
    @Override
    public void Execute(Frame frame) {
        Object ref = frame.OperandStack().PopRef();
        if (ref == null) {
            Base.Branch(frame, this.Offset);
        }
    }
}
