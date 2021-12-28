package com.test.ch06;

public class CircleXXX {

    public static float PI;

    public float r;

    public static void main(String[] args) {
        CircleXXX.PI = 3.14f;
        CircleXXX c = new CircleXXX();
        c.r = 5.5f;

        float area = CircleXXX.PI * c.r * c.r;
     //   System.out.println(area);
    }

}
