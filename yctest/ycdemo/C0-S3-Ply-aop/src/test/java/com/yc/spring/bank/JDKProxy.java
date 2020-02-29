package com.yc.spring.bank;

import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

import org.springframework.cglib.proxy.InvocationHandler;

/**
 * aop动态代理的实现方式：
 * 1.Java官方实现方式：jdk动态代理
 * 		要求对象必须实现业务接口，基于动态接口实现方式
 * 2.cglib动态代理
 * 		不需要接口，基于继承的方式实现
 */
public class JDKProxy implements InvocationHandler, java.lang.reflect.InvocationHandler {

	//目标对象，真实主题
	private Object targetObject;
	/**
	 * 调用目标对象方法，当目标对象的方法执行时，那么就会执行当前方法
	 * proxy 代理对象
	 * method执行的方法
	 * args参数数组
	 */

	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		System.out.println("准备好玩具==="+method.getName());
		Object ret = method.invoke(targetObject, args);
		System.out.println("收拾好玩具===="+ret);
		return ret;
	}
	
	/**
	 * 生成代理对象
	 */
	@SuppressWarnings("unchecked")
	public <E> E buildProxy(E targetobject) {
		this.targetObject = targetobject;
		/**
		 * loader 目标对象的类加载器
		 * interface 目标对象实现的接口
		 */
		return (E) Proxy.newProxyInstance(
				targetobject.getClass().getClassLoader()
				, targetobject.getClass().getInterfaces(), 
				this);
				
	}
	/**
	 * 使用jdk提供的接口和工具类
	 */
	public static void main(String[] args) {
		//真实主题 == 》被代理对象
		A reall = new Real();
		
		JDKProxy jdkProxy = new JDKProxy();
		A  reall1 = jdkProxy.buildProxy(reall);
		reall1.play();
	}

}
