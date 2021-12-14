package com.test.ch07;

public class Demo {

	static class Parent{

	}

	static class Child1 extends Parent {}
	static class Child2 extends Parent{}

	public void sayHello(Child1 child1) {
		System.out.println("child1 is saying");
	}

	public void sayHello(Child2 child2) {
		System.out.println("child2 is saying");
	}

	public void sayHello(Parent p) {
		System.out.println("Parent is saying");
	}

	public static void main(String[] args) {
		//多态  静态类型是Parent  实例类型是Child1
		Parent p1 = new Child1();
		Parent p2 = new Child2();
		Demo d = new Demo();

		//结果会是什么？
		d.sayHello(p1);
		d.sayHello((Child2) p2);
	}
}
