package com.jvm.instructions.control;


import com.jvm.instructions.base.Base;
import com.jvm.instructions.base.BytecodeReader;
import com.jvm.instructions.base.Instruction;
import com.jvm.rtda.Frame;
import lombok.experimental.var;

/*
tableswitch
<0-3 byte pad>
defaultbyte1
defaultbyte2
defaultbyte3
defaultbyte4
lowbyte1
lowbyte2
lowbyte3
lowbyte4
highbyte1
highbyte2
highbyte3
highbyte4
jump offsets...
*/
// Access jump table by index and jump
public class TABLE_SWITCH implements Instruction {

    public int defaultOffset;
    public int low;
    public int high;
    public int jumpOffsets[];

    @Override
    public void FetchOperands(BytecodeReader reader) {
        reader.SkipPadding();
        this.defaultOffset = reader.ReadInt32();							//defaultOffset对应默认情况下执行跳转所需的字节码偏移量;
        this.low = reader.ReadInt32();							//low和high记录case的取值范围;
        this.high = reader.ReadInt32();
        int jumpOffsetsCount = this.high - this.low + 1;			//jumpOffsets是一个索引表，里面存 放high-low+1个int值，对应各种case情况下，执行跳转所需的字节 码偏移量。
        this.jumpOffsets = reader.ReadInt32s(jumpOffsetsCount);
    }

    @Override
    public void Execute(Frame frame) {
        int index = frame.OperandStack().PopInt();
        int  offset ;
        if (index >= this.low && index <= this.high ){
            offset = this.jumpOffsets[index-this.low];
        } else {
            offset = this.defaultOffset;
        }
        Base.Branch(frame, offset);
    }
}
