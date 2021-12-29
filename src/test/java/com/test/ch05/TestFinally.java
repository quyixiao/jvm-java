package com.test.ch05;

public class TestFinally {

    public  static  int test(){
        int i = 1;
        try{
            i = 2 ;
            int a = 0 ;
            int b = 1;
          int c = b / a ;
            return i ;
        }catch (Exception e ){
            i = 3 ;
            return i ;
        }finally {
            i = 4 ;
        }
    }
    public static void main(String[] args) {
        int i = test();
        System.out.println(i);
    }
}
