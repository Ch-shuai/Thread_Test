package dynamicProxy;

/**
 * 2020/5/26
 *
 * @author wuzhanhao
 * <p>
 * description:
 *
 */
public class Student implements User {

	@Override
	public void eat() {
		System.out.println("学生吃饭");
	}

	@Override
	public void sing() {
		System.out.println("学生唱歌");

	}
}
