package com.test.ch09;



public class GetClassTest {

    public static void main(String[] a     ) {
        String voidName = void.class.getName(); // void
        String boolName = boolean.class.getName(); // boolean
        String byteName = byte.class.getName(); // byte
        String charName = char.class.getName(); // char
        String shortName = short.class.getName(); // short
        String intName = int.class.getName(); // int
        String longName = long.class.getName(); // long
        String floatName = float.class.getName(); // float
        String doubleName = double.class.getName(); // double
        String objectName = Object.class.getName(); // java.lang.Object
        String GetClassTestName = GetClassTest.class.getName(); // jvmgo.book.ch09.GetClassTest
        String AintName = int[].class.getName(); // [I
        String AAIntName = int[][].class.getName(); // [[I
        String AObjectName = Object[].class.getName(); // [Ljava.lang.Object;
        String AAObjectName = Object[][].class.getName(); // [[Ljava.lang.Object;
        String RunnableName = Runnable.class.getName(); // java.lang.Runnable
        String abcName = "abc".getClass().getName(); // java.lang.String
        String ADoubleName = new double[0].getClass().getName();// [D
        String AStringName = new String[0].getClass().getName(); // [Ljava.lang.String;
    }

}
