package help;

import java.util.concurrent.CountDownLatch;

/**
 * 2020/5/15
 *
 * @author wuzhanhao
 * <p>
 * description: 辅助类
 * 允许一个或多个线程等待直到在其他线程中执行的一组操作完成的同步辅助。 一个计数器
 * 一个CountDownLatch初始化N可以用来做一个线程等待，直到N个线程完成某项操作，或某些动作已经完成N次。
 *      相当于CountDownLatch是一个减法计数器，一个线程完整跑一次则次数-1
 */
public class CountDownLatchDemo {
    public static void main(String[] args) throws InterruptedException {
        //计数器的数量为线程的数量
        CountDownLatch countDownLatch = new CountDownLatch(7);
        //线程-1
        for (int i = 1; i <= 7; i++) {
            countDownLatch.countDown();
            long count = countDownLatch.getCount();
            int finalI = i;
            new Thread(() -> {
                System.out.println("Thread.currentThread().getName()" + Thread.currentThread().getName() + "i=>" + finalI);
                System.out.println(count);
            }, String.valueOf(i)).start();
        }
        //等待计数器归零，在向下执行
        countDownLatch.await();

        System.out.println("Close door");
    }
}
