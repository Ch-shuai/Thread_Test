package staicProxy;

/**
 * 2020/5/26
 *
 * @author wuzhanhao
 * <p>
 * description:
 * 		UserDao 这个对象的代理对象,由于继承了相对应的interface所以和普通类差不多
 */
public class UserDaoProxy implements userDao {
	private userDao userDao;

	public UserDaoProxy(userDao userDao){
		this.userDao = userDao;
	}

	@Override
	public void save() {
		System.out.println("-----开始事务-------");
		userDao.save();
		System.out.println("--------结束事务-------");
	}
}
