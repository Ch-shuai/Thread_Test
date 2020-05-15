package help;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/**
 * 2020/5/15
 *
 * @author wuzhanhao
 * <p>
 * description:
 * 操作信号量，并发限流，控制最大线程数
 */
public class SemaphoreDemo {
    public static void main(String[] args) {
        Semaphore semaphore = new Semaphore(3);
        for (int i = 1; i <= 6; i++) {
            new Thread(() -> {
                try {
                    //acquire()得到，假设如果已经满了，等待，等到线程被释放
                    semaphore.acquire();
                    System.out.println(Thread.currentThread().getName() + "抢到车位");
                    TimeUnit.SECONDS.sleep(5);
                    System.out.println(Thread.currentThread().getName() + "离开车位");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    //release()释放
                    semaphore.release();
                }
            }, String.valueOf(i)).start();
        }
    }
}
