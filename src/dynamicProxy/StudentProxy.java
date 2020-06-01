package dynamicProxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * 2020/5/26
 *
 * @author wuzhanhao
 * <p>
 * description:
 * 学生的动态代理类
 */
public class StudentProxy {
	/**
	 * 由于只是一个代理模式，所以实际上调用的还是student
	 */
	Student student = new Student();

	public User getProxy() {
		/**
		 * public static Object newProxyInstance(ClassLoader loader,Class<?>[] interfaces,InvocationHandler h)
		 * ClassLoader loader，	类加载器，代理类的类加载器
		 * Class<?>[] interfaces，获取被代理类的接口
		 * InvocationHandler h，由代理实例的调用处理程序实现的接口
		 */
		Object proxyInstance = Proxy.newProxyInstance(StudentProxy.class.getClassLoader(), Student.class.getInterfaces(), new InvocationHandler() {
			/**处理代理实例上的方法调用并返回结果
			 * @param proxy    调用该方法的代理实例
			 * @param method
			 * @param args
			 * @return
			 * @throws Throwable
			 */
			@Override
			public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
				System.out.println("学生开始自由活动");
				Object invoke = method.invoke(student, args);
				System.out.println("学生结束了自由活动");
				return invoke;
			}
		});
		return (User) proxyInstance;
	}
}
