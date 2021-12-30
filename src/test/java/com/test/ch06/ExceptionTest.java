package com.test.ch06;

public class ExceptionTest {


    public static void main(String[] args) {
        try {
            int a = 0;

            if (a == 1) {
                throw new ExceptionA();
            }
            a = 3;
            if (a == 2) {
                throw new ExceptionB();
            }
            a = 4;
        } catch (ExceptionA e) {
            e.printStackTrace();
        } catch (ExceptionB b) {
            b.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }


    }
}
