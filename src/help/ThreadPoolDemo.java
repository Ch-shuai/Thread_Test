package help;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 2020/5/18
 *
 * @author wuzhanhao
 * <p>
 * description:
 *  线程池
 *          三大方法
 *          newSingleThreadExecutor()，newCachedThreadPool()，newFixedThreadPool()
 *          使用了线程池之后需要使用线程池来创建线程方法
 *          newSingleThreadExecutor,创建一个使用无界队列的单个工作线程的执行程序，
 *          newCachedThreadPool,创建一个缓存线程池，调用execute将重用以前构造的线程。 如果没有可用的线程，将创建一个新的线程并将其添加到该池中。 未使用六十秒的线程将被终止并从缓存中删除。 因此，长时间保持闲置的池将不会消耗任何资源
 *          newFixedThreadPool,需要固定线程池的最大初始容量，
 *
 */
public class ThreadPoolDemo {
    public static void main(String[] args) {
        //创建单个线程,创建一个使用从无界队列运行的单个工作线程的执行程序
        ExecutorService threadExecutor = Executors.newSingleThreadExecutor();
        //可伸缩的线程池大小
        ExecutorService executorService = Executors.newCachedThreadPool();
        //创建一个固定的线程池大小
        ExecutorService executorService1 = Executors.newFixedThreadPool(100);
        for (int i = 0; i <100; i++) {
            //线程池执行
            threadExecutor.execute(()->{
                System.out.println(Thread.currentThread().getName() + " -----------------> OK");
            });
        }

        threadExecutor.shutdown();
    }
}
