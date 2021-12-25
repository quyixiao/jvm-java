package com.jvm.rtda;

import com.jvm.rtda.heap.JObject;
import com.jvm.utils.ByteUtil;
import lombok.Data;

@Data
public class OperandStack {
    public int size ;
    public Slot[] slots;

    public OperandStack() {
    }

    // 操作数栈的大小是编译器已经确定的，所以可以用[]Slot实现。 size字段用于记录栈顶位置
    public OperandStack(Integer maxStack) {
        this.slots = new Slot[maxStack];
    }


    //和局部变量表类似，需 要定义一些方法从操作数栈中弹出，或者往其中推入各种类型的变量
    //PushInt()方法往栈顶放一个int变量，然后把size加1。PopInt() 方法则恰好相反，先把size减1，然后返回变量值
    public void PushInt(int val) {
        initSlot(this.size);
        this.slots[this.size].num = val;
        this.size++;
    }


    public Integer PopInt() {
        this.size--;
        return this.slots[this.size].num;
    }

    public void PushFloat(Float val) {
        initSlot(this.size);
        byte bits[] = ByteUtil.getBytes(val);
        this.slots[this.size].num = ByteUtil.getInt(bits);
        this.size++;
    }

    public Float PopFloat() {
        this.size--;
        byte[] bits = ByteUtil.getBytes(this.slots[this.size].num);
        return ByteUtil.getFloat(bits);
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


    // long consumes two slots
//把long变量推入栈顶时，要拆成两个int变量。弹出时，先弹出 两个int变量，然后组装成一个long变量。
    public void PushLong(Long val) {
        initSlot(this.size);
        initSlot(this.size + 1);
        byte[] longByte = ByteUtil.getBytes(val);
        byte[] a = new byte[4];
        byte[] b = new byte[4];
        System.arraycopy(longByte, 0, a, 0, 4);
        System.arraycopy(longByte, 4, b, 0, 4);
        this.slots[size].num = ByteUtil.getInt(a);
        this.slots[size + 1].num = ByteUtil.getInt(b);
        this.size += 2;
    }


    public Long PopLong() {
        this.size -= 2;
        Integer low = this.slots[size].num;
        Integer high = this.slots[size + 1].num;
        byte[] a = ByteUtil.getBytes(low);
        byte[] b = ByteUtil.getBytes(high);
        byte[] longByte = new byte[8];
        System.arraycopy(a, 0, longByte, 0, 4);
        System.arraycopy(b, 0, longByte, 4, 4);
        return ByteUtil.getLong(longByte);
    }


    // double consumes two slots
//double变量先转成long类型，然后按long变量处理。
    public void PushDouble(Double val) {
        byte bits[] = ByteUtil.getBytes(val);
        this.PushLong(ByteUtil.getLong(bits));
    }

    public Double PopDouble() {
        byte bits[] = ByteUtil.getBytes(this.PopLong());
        return ByteUtil.getDouble(bits);
    }

    public void PushRef(JObject ref) {
        initSlot(this.size);
        this.slots[this.size].ref = ref;
        this.size++;
    }

    //弹出引用后，把Slot结构体的ref字段设置成nil，这样 做是为了帮助Go的垃圾收集器回收Object结构体实例。
    public JObject PopRef() {
        this.size--;
        JObject ref = this.slots[this.size].ref;
        this.slots[this.size].ref = null;
        return ref;
    }

    public void PushSlot(Slot slot) {
        this.slots[this.size] = slot;
        this.size++;
    }

    public Slot PopSlot() {
        this.size--;
        return this.slots[this.size];
    }


    public void initSlot(int i) {
        if (this.slots[i] == null) {
            this.slots[i] = new Slot();
        }
    }



    public  void   Clear() {
        this.size = 0;
        for (int i = 0 ;i < this.slots.length ;i ++){
            this.slots[i].ref = null;
        }
    }


    public JObject GetRefFromTop(int n ) {
        return this.slots[this.size-1-n].ref;
    }


    public void  PushBoolean(boolean val ) {
        if (val) {
            this.PushInt(1);
        } else {
            this.PushInt(0);
        }
    }


    public boolean PopBoolean() {
        return this.PopInt() == 1;
    }


    public OperandStack  NewOperandStack(int maxStack ) {
        return new OperandStack(maxStack);
    }


    public static void main(String[] args) {
        OperandStack operandStack = new OperandStack(100);
        int a = Integer.MAX_VALUE - 8;
        operandStack.PushFloat( (float) a);
        System.out.println(operandStack.PopFloat());
    }

}
