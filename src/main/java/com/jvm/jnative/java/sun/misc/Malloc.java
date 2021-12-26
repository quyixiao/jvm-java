package com.jvm.jnative.java.sun.misc;

import com.jvm.utils.ExceptionUtils;

import java.util.HashMap;
import java.util.Map;

public class Malloc {

    public static Map<Long, byte[]> _allocated = new HashMap<>();
    public static long _nextAddress = 64l; // not zero!

    public static long allocate(long size) {
        byte[] mem = new byte[(int) size];
        long address = _nextAddress;
        _allocated.put(address, mem);
        _nextAddress += size;
        return address;
    }


    public static long reallocate(long address, long size) {
        if (size == 0) {
            return 0;
        } else if (address == 0) {
            return allocate(size);
        } else {
            byte[] mem = memoryAt(address);
            if (mem.length >= size) {
                return address;
            } else {
                _allocated.remove(address);
                long newAddress = allocate(size);
                byte[] newMem = memoryAt(newAddress);
                System.arraycopy(mem,0 ,newMem,0 ,mem.length );
                return newAddress;
            }
        }
    }

    public static void free(long address) {
        byte[] b = _allocated.get(address);
        if (b != null) {
            _allocated.remove(address);
        } else {
            ExceptionUtils.throwException("memory was not allocated!");
        }
    }

    public static byte[] memoryAt(long address) {
        for (Map.Entry<Long, byte[]> map : _allocated.entrySet()) {
            long startAddress = map.getKey();
            byte[] mem = map.getValue();
            long endAddress = startAddress + mem.length;

            if (address >= startAddress && address < endAddress) {
                long offset = address - startAddress;
                byte result[] = new byte[mem.length - (int) offset];
                System.arraycopy(mem, (int) offset, result, 0, result.length);
                return result;
            }
        }
        ExceptionUtils.throwException("invalid address!");
        return null;
    }


}
