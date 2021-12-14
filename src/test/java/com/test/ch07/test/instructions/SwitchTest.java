package com.test.ch07.test.instructions;

public class SwitchTest {

    public static void main(String[] args) {
        chooseNear(1);
        chooseFar(2);
    }

    // tableswitch
    private static int chooseNear(int i) {
        switch (i) {
            case 0:  return  0;
            case 1:  return  1;
            case 2:  return  2;
            default: return -1;
        }
    }

    // lookupswitch
    private static int chooseFar(int i) {
        switch (i) {
            case -100: return -1;
            case 0:    return  0;
            case 100:  return  1;
            default:   return -1;
        }
    }

}
