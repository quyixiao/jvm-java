package com.test.ch07;

public class FibonacciTest {

    public static void main(String[] args) {
        long x = fibonacci(3);
        System.out.println(x);
    }

    private static long fibonacci(long n) {
        if (n <= 1) {
            return n;
        } else {
            return fibonacci(n - 1) + fibonacci(n - 2);
        }
    }

}
