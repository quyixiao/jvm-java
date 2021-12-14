package com.test.ch07;

public class Demo2 {
	public void sayHello(byte i) {
		System.out.println("byte");
	}
	public void sayHello(short i) {
		System.out.println("short");
	}
	public void sayHello(int i) {
		System.out.println("int");
	}
	public void sayHello(int... i) {
		System.out.println("int...");
	}
	public void sayHello(long i) {
		System.out.println("long");
	}

	public static void main(String[] args) {
		Demo2 d = new Demo2();
		d.sayHello(2);  //上面的方法好像看似都可以，到底哪个被调用呢?
	}
}
