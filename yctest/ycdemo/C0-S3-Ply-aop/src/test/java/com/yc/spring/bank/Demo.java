package com.yc.spring.bank;

/**
 * 
 * @author psq
 *
 */
public class Demo {
	public static void main(String[] args) {
		A real = new Real();
		System.out.println("为代理的对象");
		real.play();
		
		A rea2 = new StaticProxy();
		System.out.println("代理的对象");
		rea2.play();
	}
}
interface A {
	void play();
}
/**
 * 真实的业务
 */
class Real implements A{

	@Override
	public void play() {
		System.out.println("开心的玩");
	}
	
}
/**
 * 代理类
 */
class StaticProxy implements A{
	Real real = new Real();

	@Override
	public void play() {
		System.out.println("准备好玩具");
		real.play();
		System.out.println("收拾好玩具");
	}
	
}
