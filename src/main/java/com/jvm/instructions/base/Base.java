package com.jvm.instructions.base;

import com.jvm.rtda.Frame;

public class Base {


    public static void Branch(Frame frame, int offset) {
        int pc = frame.Thread().PC();
        int nextPC = pc + offset;
        frame.SetNextPC(nextPC);
    }


}
