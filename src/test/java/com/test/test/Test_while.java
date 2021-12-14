package com.test.test;

public class Test_while {


    int chooseNear(int i) {
        switch (i) {
            case 0:
                return 0;
            case 1:
                return 1;
            case 2:
                return 2;
            default:
                return -1;
        }
    }


    int chooseFar(int i) {
        switch (i) {
            case -100:
                return -1;
            case 0:
                return 0;
            case 100:
                return 1;
            default:
                return -1;
        }
    }

    int[][][] create3DArray() {
        int grid[][][];
        grid = new int[10][5][];
        return grid;
    }


    void createThreadArray() {
        Thread threads[];
        int count = 10;
        threads = new Thread[count];
        threads[0] = new Thread();
    }

    void createBuffer() {
        int buffer[];
        int bufsz = 100;
        int value = 12;
        buffer = new int[bufsz];
        buffer[10] = value;
        value = buffer[11];
    }


    int addTwo(int i, int j) {
        return i + j;
    }

    int add12and13() {
        return addTwo(12, 13);
    }


    int lessThan100(double d) {
        if (d < 100.0) {
            return 1;
        } else {
            return -1;
        }
    }

    void whileDouble() {
        double i = 0.0;
        while (i < 100.1) {
            i++;
        }
    }

    void whileInt() {
        int i = 2;
        while (i < 100) {
            i++;
        }
    }

    public static void main(String[] args) {
        System.out.println("");
    }
}
