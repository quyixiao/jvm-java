package com.jvm.instructions.constants.ldc;

import com.jvm.instructions.base.Index8Instruction;
import com.jvm.rtda.Frame;

// Push item from run-time constant pool
// ldc 和ldc_w 指令用于访问运行时常量池中的值，这包括类String的实例，但不包括double和long类型的实例
// 当运行时常量池中的条目过多时，需要使用ldc_w 指令取代ldc指令来访问常量池，ldc2_w 指令用于访问类型为double和long的运行时常量池项目
// 这个指令没有非宽版本。
public class LDC extends Index8Instruction {
    @Override
    public void Execute(Frame frame) {
        _ldc(frame, this.Index);
    }
}
