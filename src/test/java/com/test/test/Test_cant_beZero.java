package com.test.test;


public class Test_cant_beZero {

    private String a;

    @Documented(value = "iewiewi",index = 2)
    public void tryItOut() throws TestExc, TestExc2 {
        int i = 0;
        double j = 1;
        float c = 1.2f;
    }


    public void tryItOut1() {

    }


    class Foo {

    }

    public void onlyMe(Foo f) {
        synchronized (f) {
            System.out.println("");
        }
    }


    // 有四种方式可以让程序退出try语句 ，1.语句埠内的所有语句正常执行结束，通过return语句退出方法。
    // 执行break或continue语句，抛出异常，如果tryItOut正常结束，没有抛出异常并返回，那后面的jsr指令会使程序跳转到finally语句块继续执行
    void tryFinally() {
        try {
            tryItOut1();
        } finally {
            wrapItUp();
        }
    }

    private void wrapItUp() {

    }


    void nestedCatch() {
        try {
            try {
                tryItOut();
            } catch (TestExc e) {
                handleExc(e);
            }
        } catch (TestExc2 e) {
            handleExc2(e);
        }
    }

    void cantBeZero(int i) {
        try {
            tryItOut();
        } catch (TestExc e) {
            handleExc(e);
        } catch (TestExc2 e) {
            handleExc2(e);
        }
    }

    public void handleExc(Exception e) {

    }

    public void handleExc2(Exception e) {

    }

    public static void main(String[] args) {

    }
}
