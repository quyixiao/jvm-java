package com.test.ch05;

public class GaussTest {

    public static void main(String[] args) {
        int i = 2;
        switch (i) {
            case 1:
                i++;
                break;
            case 2:
                i--;
                break;
            default:
                i = i + 2;
                break;
        }

    }

}
