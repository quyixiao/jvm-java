package com.jvm.rtda.heap;

import com.jvm.data.Uint16;
import com.jvm.utils.ExceptionUtils;

public class JObject {
    public JClass jClass;    //一个存放对象的Class指针
    public Object data; // Slots for Object, []int32 for int[] ...
    public Object extra;       //extra字段用来记录Object结构体实例的额外信息


    public JObject(JClass jClass) {
        this.jClass = jClass;
    }

    public JObject(JClass jClass, Object data, Object extra) {
        this.jClass = jClass;
        this.data = data;
        this.extra = extra;
    }

    // getters
    public JClass Class() {
        return this.jClass;
    }

    public Object Data() {
        return this.data;
    }

    public Slots Fields() {
        return (Slots) this.data;
    }

    public Object Extra() {
        return this.extra;
    }


    public void SetExtra(Object extra) {
        this.extra = extra;
    }

    public boolean IsInstanceOf(JClass jClass) {
        return jClass.IsAssignableFrom(this.jClass);
    }

    // reflection
    public JObject GetRefVar(String name, String descriptor) {
        JField field = this.jClass.getField(name, descriptor, false);
        Slots slots = (Slots) this.data;
        return slots.GetRef(field.slotId);
    }

    public void SetRefVar(String name, String descriptor, JObject ref) {
        JField field = this.jClass.getField(name, descriptor, false);
        Slots slots = (Slots) this.data;
        slots.SetRef(field.slotId, ref);
    }

    public void SetIntVar(String name, String descriptor, int val) {
        JField field = this.jClass.getField(name, descriptor, false);
        Slots slots = (Slots) this.data;
        slots.SetInt(field.slotId, val);
    }

    public int GetIntVar(String name, String descriptor) {
        JField field = this.jClass.getField(name, descriptor, false);
        Slots slots = (Slots) this.data;
        return slots.GetInt(field.slotId);
    }


    public byte[] Bytes() {
        return (byte[]) this.data;
    }

    public short[] Shorts() {
        return (short[]) this.data;
    }

    public int[] Ints() {
        return (int[]) this.data;
    }

    public long[] Longs() {
        return (long[]) this.data;
    }

    public char[] Chars() {
        return (char[]) this.data;
    }

    public float[] Floats() {
        return (float[]) this.data;
    }

    public double[] Doubles() {
        return (double[]) this.data;
    }


    public JObject[] Refs() {
        return (JObject[]) this.data;
    }


    public int ArrayLength() {
        if (this.data instanceof byte[]) {
            return ((byte[]) this.data).length;
        } else if (this.data instanceof short[]) {
            return ((short[]) this.data).length;
        } else if (this.data instanceof char[]) {
            return ((char[]) this.data).length;
        } else if (this.data instanceof int[]) {
            return ((int[]) this.data).length;
        } else if (this.data instanceof float[]) {
            return ((float[]) this.data).length;
        } else if (this.data instanceof long[]) {
            return ((long[]) this.data).length;
        } else if (this.data instanceof double[]) {
            return ((double[]) this.data).length;
        } else if (this.data instanceof JObject[]) {
            return ((JObject[]) this.data).length;
        } else {
            ExceptionUtils.throwException("Not array!");
        }
        return 0;
    }

    public void ArrayCopy(JObject src, JObject dst, int srcPos, int dstPos, int length) {
        if (this.data instanceof byte[]) {
            byte _src[] = (byte[]) src.data;
            byte _dst[] = (byte[]) src.data;
            System.arraycopy(_src, srcPos, _dst, dstPos, length);
        } else if (this.data instanceof short[]) {
            short _src[] = (short[]) src.data;
            short _dst[] = (short[]) src.data;
            System.arraycopy(_src, srcPos, _dst, dstPos, length);
        } else if (this.data instanceof char[]) {
            char _src[] = (char[]) src.data;
            char _dst[] = (char[]) src.data;
            System.arraycopy(_src, srcPos, _dst, dstPos, length);
        } else if (this.data instanceof int[]) {
            int _src[] = (int[]) src.data;
            int _dst[] = (int[]) src.data;
            System.arraycopy(_src, srcPos, _dst, dstPos, length);
        } else if (this.data instanceof float[]) {
            float _src[] = (float[]) src.data;
            float _dst[] = (float[]) src.data;
            System.arraycopy(_src, srcPos, _dst, dstPos, length);
        } else if (this.data instanceof long[]) {
            long _src[] = (long[]) src.data;
            long _dst[] = (long[]) src.data;
            System.arraycopy(_src, srcPos, _dst, dstPos, length);
        } else if (this.data instanceof double[]) {
            double _src[] = (double[]) src.data;
            double _dst[] = (double[]) src.data;
            System.arraycopy(_src, srcPos, _dst, dstPos, length);
        } else if (this.data instanceof JObject[]) {
            JObject _src[] = (JObject[]) src.data;
            JObject _dst[] = (JObject[]) src.data;
            System.arraycopy(_src, srcPos, _dst, dstPos, length);
        } else {
            ExceptionUtils.throwException("Not array!");
        }

    }


}
