package com.yc.spring.bank;

import java.lang.reflect.Method;
import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

/**
 * 动态字节码技术，动态生成目标对象的子类对象 Enhancer类 MethodInterface接口
 * 
 * @author psq
 *
 */
public class CGLIBProxy implements MethodInterceptor {

	private Object targetObject;

	@Override
	/**
	 * 代理对象 子类的方法 参数列表 父类的方法
	 */
	public Object intercept(Object arg0, Method method, Object[] arg2, MethodProxy supermethod) throws Throwable {
		System.out.println("准备好玩具===" + method.getName());
		Object ret = supermethod.invoke(targetObject, arg2);
		System.out.println("收拾好玩具===" + ret);
		return ret;
	}

	@SuppressWarnings("unchecked")
	public <E> E buildProxy(E targetobject) {
		this.targetObject = targetobject;
		Enhancer enhancer = new Enhancer();
		// 设置父类的方法
		enhancer.setSuperclass(targetobject.getClass());
		// 设置方法的回调方法
		enhancer.setCallback(this);
		// 创建代理对象
		return (E) enhancer.create();
	}

	public static void main(String[] args) {
		// 真实主题 == 》被代理对象
		A reall = new Real();

		CGLIBProxy cgiProxy = new CGLIBProxy();
		A reall1 = cgiProxy.buildProxy(reall);
		reall1.play();
	}
}
