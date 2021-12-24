package com.jvm.rtda.heap;

import com.jvm.data.Uint16;
import com.jvm.utils.ExceptionUtils;

public class JObject  {
    public JClass jClass;    //一个存放对象的Class指针
    public Object data; // Slots for Object, []int32 for int[] ...
    public Object extra;       //extra字段用来记录Object结构体实例的额外信息


    public JObject(JClass jClass) {
        this.jClass = jClass;
        if(jClass.name .equals("[Ljava/util/Hashtable$Entry;")){
            System.out.println("-----------------");
        }
        this.data = new Slots(this.jClass.instanceSlotCount);
    }

    public JObject(JClass jClass, Object data, Object extra) {
        this.jClass = jClass;
        this.data = data;
        if(jClass.name .equals("[Ljava/util/Hashtable$Entry;")){
            System.out.println("-----------------");
        }
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

    public char [] Chars() {
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
        } else if (this.data instanceof Uint16[]) {
            return ((char[]) this.data).length;
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


    public JObject Clone() {
        return new JObject(
                this.jClass,
                this.cloneData(), null);
    }

    public Object cloneData() {
        if (this.data instanceof byte[]) {
            byte[] elements = (byte[]) this.data;
            byte elements2[] = new byte[elements.length];
            System.arraycopy(elements, 0, elements2, 0, elements.length);
            return elements2;
        } else if (this.data instanceof short[]) {
            short[] elements = (short[]) this.data;
            short[] elements2 = new short[elements.length];
            System.arraycopy(elements, 0, elements2, 0, elements.length);
            return elements2;
        } else if (this.data instanceof char[]) {
            char[] elements = (char[]) this.data;
            char[] elements2 = new char[elements.length];
            System.arraycopy(elements, 0, elements2, 0, elements.length);
            return elements2;
        } else if (this.data instanceof int[]) {
            int[] elements = (int[]) this.data;
            int[] elements2 = new int[elements.length];
            System.arraycopy(elements, 0, elements2, 0, elements.length);
            return elements2;
        } else if (this.data instanceof long[]) {
            long[] elements = (long[]) this.data;
            long[] elements2 = new long[elements.length];
            System.arraycopy(elements, 0, elements2, 0, elements.length);
            return elements2;
        } else if (this.data instanceof float[]) {
            float[] elements = (float[]) this.data;
            float[] elements2 = new float[elements.length];
            System.arraycopy(elements, 0, elements2, 0, elements.length);
            return elements2;
        } else if (this.data instanceof double[]) {
            double[] elements = (double[]) this.data;
            double[] elements2 = new double[elements.length];
            System.arraycopy(elements, 0, elements2, 0, elements.length);
            return elements2;
        } else if (this.data instanceof JObject[]) {
            JObject[] elements = (JObject[]) this.data;
            JObject[] elements2 = new JObject[elements.length];
            System.arraycopy(elements, 0, elements2, 0, elements.length);
            return elements2;
        } else {// []Slot
            Slots slots = (Slots) this.data;
            Slots slots2 = new Slots(slots.slots.length);
            for (int i = 0; i < slots.slots.length; i++) {
                slots2.slots[i] = slots.slots[i].clone();
            }
            return slots2;
        }
    }




}
