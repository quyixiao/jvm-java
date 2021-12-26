package com.jvm.instructions.math.iinc;

import com.jvm.instructions.base.BytecodeReader;
import com.jvm.instructions.base.Index8Instruction;
import com.jvm.instructions.base.Instruction;
import com.jvm.rtda.Frame;
import com.jvm.rtda.LocalVars;

// Increment local variable by constant
public class IINC extends Index8Instruction {

    public int Index;
    public int Const;


    @Override
    public void FetchOperands(BytecodeReader reader) {
        this.Index = reader.ReadUint8().Value();
        this.Const = reader.ReadInt8();
    }

    @Override
    public void Execute(Frame frame) {
        LocalVars localVars = frame.LocalVars();
        Integer val = localVars.GetInt(this.Index);
        val += this.Const;
        localVars.SetInt(this.Index, val);
    }
}
