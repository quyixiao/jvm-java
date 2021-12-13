package com.jvm.classfile;

import com.jvm.data.Uint16;
import com.jvm.data.Uint32;
import com.jvm.data.Uint64;
import com.jvm.data.Uint8;

public class ClassReader {


    byte[] data;


    public ClassReader(byte[] data) {
        this.data = data;
    }

    // u1
    public Uint8 readUint8() {
        int value = readByte8(0);
        copyArray(1);
        return new Uint8(value);
    }

    // u2
    public Uint16 readUint16() {
        int value = readByte16(0);
        copyArray(2);
        return new Uint16(value);
    }

    // u4
    public Uint32 readUint32() {
        int value = readByte32(0);
        copyArray(4);
        return new Uint32(value);
    }

    // u8
    public Uint64 readUint64() {
        long value = readByte64(0);
        copyArray(8);
        return new Uint64(value);
    }


    public Uint16[] readUint16s() {
        Uint16 n = readUint16();
        Uint16[] uint16s = new Uint16[n.Value()];
        for (int i = 0; i < n.Value(); i++) {
            uint16s[i] = readUint16();
        }
        return uint16s;
    }

    public byte[] readBytes(int n) {
        byte[] data1 = new byte[n];
        byte[] data2 = new byte[this.data.length - n];
        System.arraycopy(this.data, 0, data1, 0, n);
        System.arraycopy(this.data, n, data2, 0, data.length - n);
        this.data = data2;
        return data1;
    }


    public void copyArray(int readerByte) {
        byte[] newData = new byte[data.length - readerByte];
        System.arraycopy(this.data, readerByte, newData, 0, data.length - readerByte);
        this.data = newData;
    }

    private int readByte8(final int index) {
        return this.data[index] & 0xFF;
    }

    private int readByte16(final int index) {
        return ((this.data[index] & 0xFF) << 8) | (this.data[index + 1] & 0xFF);
    }

    private int readByte32(final int index) {
        return ((this.data[index] & 0xFF) << 24) | ((this.data[index + 1] & 0xFF) << 16)
                | ((this.data[index + 2] & 0xFF) << 8) | (this.data[index + 3] & 0xFF);
    }

    private long readByte64(final int index) {
        long l1 = readByte32(index);
        long l0 = readByte32(index + 4) & 0xFFFFFFFFL;
        return (l1 << 32) | l0;
    }


}
