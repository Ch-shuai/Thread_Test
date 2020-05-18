package help;

import java.util.concurrent.locks.ReentrantLock;

/**
 * 2020/5/18
 *
 * @author wuzhanhao
 * <p>
 * description:
 * 创建一个ReentrantLock ,true为公平锁，false为非公平锁
 */
public class ReentrantLockDemo {

    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                TestDemo testDemo = new TestDemo();
                testDemo.demo1();
            }, String.valueOf(i)).start();
        }
        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                TestDemo testDemo = new TestDemo();
                testDemo.demo2();
            }, String.valueOf(i)).start();
        }
    }
}

class TestDemo {
    //创建一个公平锁
    ReentrantLock reentrantLock = new ReentrantLock(true);

    public void demo1() {
        reentrantLock.lock();
        try {
            System.out.println(Thread.currentThread().getName() + "号在打饭");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            reentrantLock.unlock();
        }
    }

    public void demo2(){
        reentrantLock.lock();
        try {
            System.out.println(Thread.currentThread().getName() + "号打好饭了");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            reentrantLock.unlock();
        }
    }
}
