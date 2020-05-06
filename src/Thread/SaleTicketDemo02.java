package Thread;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 2020/4/29
 *
 * @author wuzhanhao
 * <p>
 * description:
 */
public class SaleTicketDemo02 {
    //多线程操作
    public static void main(String[] args) {
        Ticket ticket = new Ticket();
        //使用lambda表达式
        new Thread(() -> { for (int i = 0; i < 60; i++) ticket.sellTicker(); }, "A").start();
        new Thread(() -> { for (int i = 0; i < 60; i++) ticket.sellTicker(); }, "B").start();
        new Thread(() -> { for (int i = 0; i < 60; i++) ticket.sellTicker(); }, "C").start();
    }

    /**使用lock，非公平锁
     *  1.先新建锁
     *  2.和try同级加锁
     *  3.finally中解锁
     */
    static class Ticket {
        //（属性，对应的方法）,
        private int number = 50;

        //新建可重入锁
        Lock lock = new ReentrantLock();

        //卖票的方式,synchronized锁对象和方法
        public void sellTicker() {
            //加锁
            lock.lock();
//            lock.tryLock();
            try{
                if (number > 0) {
                    System.out.println(Thread.currentThread().getName() + "卖出了" + (number--) + "张票" + "+" + "剩余" + number + "张票");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }finally {
                //解锁
                lock.unlock();
            }

        }
    }
}
