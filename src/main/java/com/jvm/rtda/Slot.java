package com.jvm.rtda;


import com.jvm.rtda.heap.JObject;
import lombok.Data;

@Data
public class Slot  implements Cloneable{


    public Integer num;

    public JObject ref;

    @Override
    public Slot clone() {
        Slot p = null;
        try {
            /**
             * 若要实现深克隆，此处就必须将对象中所有的复合数据类型统统单独复制拷贝一份，
             * 但是实际开发中，无法确定对象中复合数据的种类和个数，
             * 因此一般不采用此种方式实现深克隆
             */
            p = (Slot) super.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return p;
    }


}
