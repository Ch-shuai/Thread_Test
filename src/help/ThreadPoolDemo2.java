package help;

import java.time.LocalDate;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 2020/5/18
 *
 * @author wuzhanhao
 * <p>
 * description:
 *  七大参数int corePoolSize,核心线程池大小
 *                   int maximumPoolSize,最大线程数
 *                   long keepAliveTime,超时等待，空闲的线程达到超时时间则会shutdown
 *                   TimeUnit unit,超时时间单位
 *                   BlockingQueue<Runnable> workQueue,阻塞队列
 *                   ThreadFactory threadFactory,线程工程，创建线程的
 *                   RejectedExecutionHandler handler拒绝策略,如果队列满了则不会处理并抛出异常
 *
 *  四大策略：
 *          AbortPolicy,被拒绝的任务的处理程序，抛出一个 RejectedExecutionException 。
 *          CallerRunsPolicy,一个被拒绝的任务的处理程序，直接在 execute方法的调用线程中运行被拒绝的任务，除非执行程序已被关闭，否则这个任务被丢弃。调用execute本身的线程运行任务。 这提供了一个简单的反馈控制机制，将降低新任务提交的速度。
 *          DiscardOldestPolicy,被拒绝的任务的处理程序，丢弃最旧的未处理请求（第一个），然后重试 execute ，除非执行程序关闭，在这种情况下，任务被丢弃。不会抛出异常
 *          DiscardPolicy，简单地删除无法执行的任务。 被拒绝的任务的处理程序静默地丢弃被拒绝的任务。不会抛出异常
 */
public class ThreadPoolDemo2 {
    public static void main(String[] args) {
        LinkedBlockingQueue<Runnable> blockingQueue = new LinkedBlockingQueue<>(3);
        //自定义线程池，最大线程如何定义，
        // CPU密集型，几核是多少就定义多少，可以让CPU效率最高
        // IO密集型。判断程序中，耗IO的线程
        int lengthOfYear = LocalDate.now().lengthOfYear();
        //获取CPU 核数
        int availableProcessors = Runtime.getRuntime().availableProcessors();
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(
                2,
                availableProcessors,
                20,
                TimeUnit.SECONDS,
                blockingQueue,
                Executors.defaultThreadFactory(),

                new ThreadPoolExecutor.CallerRunsPolicy());

        try{
            // 最大承载，阻塞队列大小+max
            for (int i = 1; i <=10; i++) {
                threadPoolExecutor.execute(()->{
                    System.out.println(Thread.currentThread().getName() + "------> "+ "OK");
                });
            }
        }finally {
            threadPoolExecutor.shutdown();
        }
    }
}
