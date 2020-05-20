package thread;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 2020/4/30
 *
 * @author wuzhanhao
 * <p>
 * description:
 * 多线程之间互相通信，使用lock
 */
public class ThreadLock {
    public static void main(String[] args) {
        Data1 data = new Data1();

        //A线程+1
        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                try {
                    data.increment();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "A").start();
        //B线程-1
        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                try {
                    data.decrement();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "B").start();
        //D线程+1
        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                try {
                    data.increment();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "C").start();
        //D线程-1
        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                try {
                    data.decrement();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "D").start();
    }
}

//等待，业务，通知
//Condition 是Object监视器方法，精准通知和唤醒线程
class Data1 {
    Lock lock = new ReentrantLock();
    Condition condition = lock.newCondition();
    private int num = 0;

    //+1
    public void increment() throws InterruptedException {
        lock.lock();
        try{
            while (num!=0){
                condition.await();
            }
            num++;
            System.out.println(Thread.currentThread().getName() + "num的数值" + num);
            condition.signalAll();
        }finally {
            lock.unlock();
        }
    }

    //-1
    public void decrement() throws InterruptedException {
        lock.lock();
        try{
            while (num==0){
                condition.await();
            }
            num--;
            System.out.println(Thread.currentThread().getName() + "num的数值" + num);
            condition.signalAll();
        }finally {
            lock.unlock();
        }

    }
}

