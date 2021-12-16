package com.jvm.rtda.heap;

import com.jvm.rtda.Slot;
import com.jvm.utils.ByteUtil;

public class Slots {
    public Slot[] slots;

    public Slots(int slotCount) {
        slots = new Slot[slotCount];
        for (int i = 0; i < slotCount; i++) {
            slots[i] = new Slot();
        }
    }

    public void SetInt(int index, int val) {
        this.slots[index].num = val;
    }

    public int GetInt(int index) {
        return this.slots[index].num;
    }

    public void SetFloat(int index, float val) {
        byte[] bits = ByteUtil.getBytes(val);
        this.slots[index].num = ByteUtil.getInt(bits);
    }

    public float GetFloat(int index) {
        byte bits[] = ByteUtil.getBytes(this.slots[index].num);
        return ByteUtil.getFloat(bits);
    }

    // long consumes two slots
    public void SetLong(Integer index, Long val) {
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
        this.slots[index].ref = ref;
    }

    public JObject GetRef(Integer index) {
        return this.slots[index].ref;
    }

}
