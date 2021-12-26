package com.jvm.instructions.base;

import com.jvm.data.Uint16;
import com.jvm.data.Uint8;
import jdk.management.resource.internal.inst.SocketOutputStreamRMHooks;

public class BytecodeReader {
    public byte[] code; // bytecodes
    public int pc;


    //code字段存放字节码，pc字段记录读取到了哪个字节。为了避 免每次解码指令都新创建一个BytecodeReader实例，
//给它定义一个 Reset()方法
    public void Reset(byte[] code, int pc) {
        this.code = code;
        this.pc = pc;
    }

    public int PC() {
        return this.pc;
    }

    //下面实现一系列的Read()方法。先看最简单的ReadUint8()方 法
    public int ReadInt8() {
        return (byte)this.ReadUint8().Value();
    }

    public Uint8 ReadUint8() {
        int i = this.code[this.pc] & 0xFF;
        this.pc++;
        return new Uint8(i);
    }


    //ReadInt16()方法调用ReadUint16()，然后把读取到的值转成 int16返回
    public int ReadInt16() {
        return this.ReadUint16().Value();
    }

    //ReadUint16()连续读取两字节
    public Uint16 ReadUint16() {
        int i = ((this.code[this.pc] & 0xFF) << 8) | (this.code[this.pc + 1] & 0xFF);
        this.pc++;
        this.pc++;
        short s = (short)i;
        return new Uint16(s);
    }

    //ReadInt32()方法连续读取4字节
    public int ReadInt32() {
        int i = ((this.code[this.pc] & 0xFF) << 24) | ((this.code[this.pc + 1] & 0xFF) << 16)
                | ((this.code[this.pc + 2] & 0xFF) << 8) | (this.code[this.pc + 3] & 0xFF);
        this.pc++;
        this.pc++;
        this.pc++;
        this.pc++;
        return i;
    }

    // used by lookupswitch and tableswitch
//还需要定义两个方法:ReadInt32s()和SkipPadding()。这两个 方法只有tableswitch和lookupswitch指令使用
    public int[] ReadInt32s(int n) {
        int ints[] = new int[n];
        for (int i = 0; i < ints.length; i++) {
            ints[i] = this.ReadInt32();
        }
        return ints;
    }


    // used by lookupswitch and tableswitch
//tableswitch指令操作码的后面有0~3字节的padding，以保证 defaultOffset在字节码中的地址是4的倍数
    public void SkipPadding() {
        while (this.pc % 4 != 0) {
            this.ReadUint8();
        }
    }


}
