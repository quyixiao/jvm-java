package com.jvm.instructions.control;


import com.jvm.instructions.base.Base;
import com.jvm.instructions.base.BytecodeReader;
import com.jvm.instructions.base.Instruction;
import com.jvm.rtda.Frame;

/*
lookupswitch
<0-3 byte pad>
defaultbyte1
defaultbyte2
defaultbyte3
defaultbyte4
npairs1
npairs2
npairs3
npairs4
match-offset pairs...
*/
// Access jump table by key match and jump
public class LOOKUP_SWITCH implements Instruction {

    public Integer defaultOffset;
    public Integer npairs;
    public int[] matchOffsets;


    @Override
    public void FetchOperands(BytecodeReader reader) {
        reader.SkipPadding();
        this.defaultOffset = reader.ReadInt32();
        this.npairs = reader.ReadInt32();
        this.matchOffsets = reader.ReadInt32s(this.npairs * 2);//matchOffsets有点像Map，它的key是case值，value是跳转偏移 量。
    }

    //Execute()方法先从操作数栈中弹出一个int变量，然后用它查找 matchOffsets，看是否能找到匹配的key。如果能，则按照value给出的 偏移量跳转，否则按照defaultOffset跳转
    @Override
    public void Execute(Frame frame) {
        Integer key = frame.OperandStack().PopInt();
        for (int i = 0; i < this.npairs * 2; i += 2) {
            if (this.matchOffsets[i] == key.intValue()) {
                int offset = this.matchOffsets[i + 1];
                Base.Branch(frame, offset);
                return;
            }
        }
        Base.Branch(frame, this.defaultOffset);
    }
}
