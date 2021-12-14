package com.test.ch04;

public class Test04 {

    public static float circumference(float r) {
        float pi = 3.14f;
        float area = 2 * pi * r;
        return area;
    }

    public static void main(String[] args) {
        circumference(1.6f);
    }
}
