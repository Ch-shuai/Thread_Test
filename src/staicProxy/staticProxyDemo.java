package staicProxy;

import java.lang.reflect.Proxy;

/**
 * 2020/5/26
 *
 * @author wuzhanhao
 * <p>
 * description:
 * 		静态代理
 */
public class staticProxyDemo {
	public static void main(String[] args) {
		userDao userDao = new userDaoImpl();
		UserDaoProxy userDaoProxy = new UserDaoProxy(userDao);
		userDaoProxy.save();
		System.out.println("Proxy.isProxyClass(userDao.getClass())-------------->" + Proxy.isProxyClass(userDao.getClass()));
		System.out.println("Proxy.isProxyClass(userDaoProxy.getClass())-------------->" + Proxy.isProxyClass(userDaoProxy.getClass()));
	}
}
