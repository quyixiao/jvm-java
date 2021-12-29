package com.test.ch03;

import com.jvm.utils.t.Tuple;
import com.jvm.utils.t.Tuple2;

public class TestTuple {

    public static Tuple2<Integer, String > test(){
        return new Tuple2(1,"a");
    }

    public static void main(String[] args) {
        Tuple2<Integer ,String > data = test();
        System.out.println(data.getFirst() + " ," +data.getSecond());
    }
}
