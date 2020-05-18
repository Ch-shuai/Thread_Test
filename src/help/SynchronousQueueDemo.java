package help;

import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.TimeUnit;

/**
 * 2020/5/18
 *
 * @author wuzhanhao
 * <p>
 * description:
 *      同步队列,不存储元素，put了一个元素就需要take取出来
 *
 */
public class SynchronousQueueDemo {
    public static void main(String[] args) {
        //同步队列
        SynchronousQueue<String> synchronousQueue = new SynchronousQueue<>();
            new Thread(()->{
                try {
                    System.out.println(Thread.currentThread().getName() + "put 1");
                    synchronousQueue.put("1");
                    System.out.println(Thread.currentThread().getName() + "put 2");
                    synchronousQueue.put("2");
                    System.out.println(Thread.currentThread().getName() + "put 3");
                    synchronousQueue.put("3");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            },"T1").start();

        new Thread(()->{
            try {
                TimeUnit.SECONDS.sleep(3);
                System.out.println(Thread.currentThread().getName() +"--->"+ synchronousQueue.take());
                TimeUnit.SECONDS.sleep(3);
                System.out.println(Thread.currentThread().getName() +"--->"+ synchronousQueue.take());
                TimeUnit.SECONDS.sleep(3);
                System.out.println(Thread.currentThread().getName() +"--->"+ synchronousQueue.take());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        },String.valueOf("T2")).start();
    }
}
