package com.jvm.instructions.constants;

import com.jvm.instructions.base.BytecodeReader;
import com.jvm.instructions.base.Instruction;
import com.jvm.rtda.Frame;
import lombok.val;

// Push byte
public class BIPUSH  implements Instruction {
    public  Integer val ;


    @Override
    public void FetchOperands(BytecodeReader reader) {
        this.val = reader.ReadInt8();
    }

    @Override
    public void Execute(Frame frame) {
        frame.OperandStack().PushInt(this.val);
    }
}
