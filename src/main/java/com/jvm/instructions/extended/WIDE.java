package com.jvm.instructions.extended;

import com.jvm.instructions.base.BytecodeReader;
import com.jvm.instructions.base.Index8Instruction;
import com.jvm.instructions.base.Instruction;
import com.jvm.instructions.loads.aload.ALOAD;
import com.jvm.instructions.loads.dload.DLOAD;
import com.jvm.instructions.loads.fload.FLOAD;
import com.jvm.instructions.loads.iload.ILOAD;
import com.jvm.instructions.loads.lload.LLOAD;
import com.jvm.instructions.math.iinc.IINC;
import com.jvm.instructions.stores.astore.ASTORE;
import com.jvm.instructions.stores.dstore.DSTORE;
import com.jvm.instructions.stores.fstore.FSTORE;
import com.jvm.instructions.stores.istore.ISTORE;
import com.jvm.instructions.stores.lstore.LSTORE;
import com.jvm.rtda.Frame;
import com.jvm.utils.ExceptionUtils;

// Extend local variable index by additional bytes
public class WIDE implements Instruction {
    public Instruction modifiedInstruction;


    //FetchOperands()方法先从字节码中读取一字节的操作码，然 后创建子指令实例，最后读取子指令的操作数
    @Override
    public void FetchOperands(BytecodeReader reader) {
        int opcode = reader.ReadUint8().Value();
        Index8Instruction inst = null;
        switch (opcode) {
            case 0x15:
                inst = new ILOAD();
                inst.Index = reader.ReadUint16().Value();
                this.modifiedInstruction = inst;
            case 0x16:
                inst = new LLOAD();
                inst.Index = reader.ReadUint16().Value();
                this.modifiedInstruction = inst;
            case 0x17:
                inst = new FLOAD();
                inst.Index = reader.ReadUint16().Value();
                this.modifiedInstruction = inst;
            case 0x18:
                inst = new DLOAD();
                inst.Index = reader.ReadUint16().Value();
                this.modifiedInstruction = inst;
            case 0x19:
                inst = new ALOAD();
                inst.Index = reader.ReadUint16().Value();
                this.modifiedInstruction = inst;
            case 0x36:
                inst = new ISTORE();
                inst.Index = reader.ReadUint16().Value();
                this.modifiedInstruction = inst;
            case 0x37:
                inst = new LSTORE();
                inst.Index = reader.ReadUint16().Value();
                this.modifiedInstruction = inst;
            case 0x38:
                inst = new FSTORE();
                inst.Index = reader.ReadUint16().Value();
                this.modifiedInstruction = inst;
            case 0x39:
                inst = new DSTORE();
                inst.Index = reader.ReadUint16().Value();
                this.modifiedInstruction = inst;
            case 0x3a:
                inst = new ASTORE();
                inst.Index = reader.ReadUint16().Value();
                this.modifiedInstruction = inst;
            case 0x84:
                IINC instx = new IINC();
                instx.Index = reader.ReadUint16().Value();
                instx.Const = reader.ReadInt16();
                this.modifiedInstruction = inst;
            case 0xa9: // ret
                ExceptionUtils.throwException("Unsupported opcode: 0xa9!");
        }
    }

    //wide指令只是增加了索引宽度，并不改变子指令操作，所以其 Execute()方法只要调用子指令的Execute()方法即可
    @Override
    public void Execute(Frame frame) {
        this.modifiedInstruction.Execute(frame);
    }
}
