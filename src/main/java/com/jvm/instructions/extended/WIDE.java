package com.jvm.instructions.extended;

import com.jvm.instructions.base.BytecodeReader;
import com.jvm.instructions.base.Instruction;
import com.jvm.rtda.Frame;

public class WIDE implements Instruction {
    public Instruction modifiedInstruction;


    //FetchOperands()方法先从字节码中读取一字节的操作码，然 后创建子指令实例，最后读取子指令的操作数
    @Override
    public void FetchOperands(BytecodeReader reader) {
       /* int opcode = reader.ReadUint8().Value();
        switch (opcode) {
            case 0x15:
                inst:= &loads.ILOAD {
            }
            inst.Index = uint(reader.ReadUint16())
            self.modifiedInstruction = inst
            case 0x16:
                inst:= &loads.LLOAD {
            }
            inst.Index = uint(reader.ReadUint16())
            self.modifiedInstruction = inst
            case 0x17:
                inst:= &loads.FLOAD {
            }
            inst.Index = uint(reader.ReadUint16())
            self.modifiedInstruction = inst
            case 0x18:
                inst:= &loads.DLOAD {
            }
            inst.Index = uint(reader.ReadUint16())
            self.modifiedInstruction = inst
            case 0x19:
                inst:= &loads.ALOAD {
            }
            inst.Index = uint(reader.ReadUint16())
            self.modifiedInstruction = inst
            case 0x36:
                inst:= &stores.ISTORE {
            }
            inst.Index = uint(reader.ReadUint16())
            self.modifiedInstruction = inst
            case 0x37:
                inst:= &stores.LSTORE {
            }
            inst.Index = uint(reader.ReadUint16())
            self.modifiedInstruction = inst
            case 0x38:
                inst:= &stores.FSTORE {
            }
            inst.Index = uint(reader.ReadUint16())
            self.modifiedInstruction = inst
            case 0x39:
                inst:= &stores.DSTORE {
            }
            inst.Index = uint(reader.ReadUint16())
            self.modifiedInstruction = inst
            case 0x3a:
                inst:= &stores.ASTORE {
            }
            inst.Index = uint(reader.ReadUint16())
            self.modifiedInstruction = inst
            case 0x84:
                inst:= &math.IINC {
            }
            inst.Index = uint(reader.ReadUint16())
            inst.Const = int32(reader.ReadInt16())
            self.modifiedInstruction = inst
            case 0xa9: // ret
                panic("Unsupported opcode: 0xa9!")
        }*/
    }

    //wide指令只是增加了索引宽度，并不改变子指令操作，所以其 Execute()方法只要调用子指令的Execute()方法即可
    @Override
    public void Execute(Frame frame) {
        this.modifiedInstruction.Execute(frame);
    }
}
