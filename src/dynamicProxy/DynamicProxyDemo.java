package dynamicProxy;

/**
 * 2020/5/26
 *
 * @author wuzhanhao
 * <p>
 * description:
 */
public class DynamicProxyDemo {
	public static void main(String[] args) {
		StudentProxy studentProxy = new StudentProxy();
		User studentProxyProxy = studentProxy.getProxy();
		studentProxyProxy.sing();
		studentProxyProxy.eat();

	}
}
