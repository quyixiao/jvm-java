package com.jvm.rtda;

import com.jvm.rtda.heap.JObject;
import com.jvm.utils.ByteUtil;

public class LocalVars {
    public Slot[] slots;

    public LocalVars() {
    }

    public LocalVars(Integer maxLocals) {
        if (maxLocals > 0) {
            this.slots = new Slot[maxLocals];
        }
    }

    public void SetInt(int index, int val) {
        initSlot(index);
        this.slots[index].num = val;
    }

    public Integer GetInt(Integer index) {
        return this.slots[index].num;
    }

    public void SetFloat(Integer index, Float val) {
        initSlot(index);
        byte[] bits = ByteUtil.float2byte(val);
        this.slots[index].num = ByteUtil.getInt(bits);
    }

    public Float GetFloat(Integer index) {
        byte[] bits = ByteUtil.getBytes(this.slots[index].num);
        return ByteUtil.getFloat(bits);
    }

    // long consumes two slots
    public void SetLong(Integer index, Long val) {
        initSlot(index);
        initSlot(index + 1 );
        byte[] longByte = ByteUtil.getBytes(val);
        byte[] a = new byte[4];
        byte[] b = new byte[4];
        System.arraycopy(longByte, 0, a, 0, 4);
        System.arraycopy(longByte, 4, b, 0, 4);
        this.slots[index].num = ByteUtil.getInt(a);
        this.slots[index + 1].num = ByteUtil.getInt(b);
    }

    public Long GetLong(Integer index) {
        Integer low = this.slots[index].num;
        Integer high = this.slots[index + 1].num;
        byte[] a = ByteUtil.getBytes(low);
        byte[] b = ByteUtil.getBytes(high);
        byte[] longByte = new byte[8];
        System.arraycopy(a, 0, longByte, 0, 4);
        System.arraycopy(b, 0, longByte, 4, 4);
        return ByteUtil.getLong(longByte);
    }

    public void SetDouble(Integer index, Double val) {
        byte[] bits = ByteUtil.getBytes(val);
        this.SetLong(index, ByteUtil.getLong(bits));
    }

    public Double GetDouble(Integer index) {
        byte bits[] = ByteUtil.getBytes(this.GetLong(index));
        return ByteUtil.getDouble(bits);
    }


    public void SetRef(int index, JObject ref) {
        initSlot(index);
        this.slots[index].ref = ref;
    }

    public JObject GetRef(Integer index) {
        return this.slots[index].ref;
    }

    public void initSlot(int i) {
        if (this.slots[i] == null) {
            this.slots[i] = new Slot();
        }
    }


    public void  SetSlot(int index ,  Slot slot) {
        this.slots[index] = slot;
    }


}
