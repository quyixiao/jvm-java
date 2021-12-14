package com.test.test;

public class Test_short {

    int align2grain(int i ,int grain){
        return ((i + grain -1 ) & ~(grain -1 ));
    }


    public static void main(String[] args) {
        for ( short i = 0 ;i < 100; i ++){
            System.out.println("------------");
        }
    }
}
