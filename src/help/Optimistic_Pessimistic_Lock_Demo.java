package help;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 2020/5/18
 *
 * @author wuzhanhao
 * <p>
 * description:
 * Optimistic_Lock
 * 乐观锁----->在程序运行，读取的时候不认为会有别的线程对数据进行修改，但是在更新数据之前会判断是否有别的线程对数据进行修改，如果进行修改则会进行报错或者自动尝试
 * Pessimistic_Lock
 * 悲观锁----->在程序运行，读取的时候一定会有别的线程对数据进行修改，因为获取数据之后会加锁，例如Synchorinzed，Lock，
 */
public class Optimistic_Pessimistic_Lock_Demo {
    public static void main(String[] args) {
        OptimisticLockDemo optimisticLockDemo = new OptimisticLockDemo();
        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                optimisticLockDemo.chifan();
            }, String.valueOf(i)).start();
        }
    }
}

class OptimisticLockDemo {

    private final Lock retrantLock = new ReentrantLock(true);

    List<String> list = new ArrayList<String>(10);

    public void dafan() {
        retrantLock.lock();
        try {
            list.add(Thread.currentThread().getName());
            System.out.println(Thread.currentThread().getName() + "号线程在插入");
        } finally {
            retrantLock.unlock();
        }
    }

    public void chifan() {
        retrantLock.lock();
        try {
            if (list != null) {
                list.get(Integer.valueOf(Thread.currentThread().getName()));
                System.out.println(Thread.currentThread().getName() + "号线程在读取");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            retrantLock.unlock();
        }
    }
}



