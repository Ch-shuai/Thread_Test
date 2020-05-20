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
 *      使用condition精准唤醒线程
 *          业务-》判断-》执行-》唤醒
 */
public class ThreadSort {
    public static void main(String[] args) {

        Data2 data2 = new Data2();
        new Thread(()->{
            for (int i = 0; i < 10; i++) {
                try {
                    data2.printA();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"A").start();
        new Thread(()->{
            for (int i = 0; i < 10; i++) {
                try {
                    data2.printB();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"B").start();
        new Thread(()->{
            for (int i = 0; i < 10; i++) {
                try {
                    data2.printC();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"C").start();
    }
}

class Data2{
    Lock lock = new ReentrantLock();
    //同步监视器
    final Condition conditionA = lock.newCondition();
    final Condition conditionB = lock.newCondition();
    final Condition conditionC = lock.newCondition();
    int number = 1;

    public void printA() throws InterruptedException {
        lock.lock();
        try{
            while (number!=1){
                conditionA.await();
            }
            System.out.println(Thread.currentThread().getName() + " --> AAAAAAAAA");
            number = 2;
            conditionB.signal();
        }finally {
            lock.unlock();
        }
    }
    public void printB() throws InterruptedException {
        lock.lock();
        try{
            while (number!=2){
                conditionB.await();
            }
            System.out.println(Thread.currentThread().getName() + " --> BBBBBBBB");
            number=3;
            conditionC.signal();
        }finally {
            lock.unlock();
        }
    }
    public void printC() throws InterruptedException {
        lock.lock();
        try{
            while (number!=3){
                conditionC.await();
            }
            System.out.println(Thread.currentThread().getName() + " --> CCCCCCCCC");
            number=1;
            conditionA.signal();
        }finally {
            lock.unlock();
        }
    }
}
