package help;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * 2020/5/18
 *
 * @author wuzhanhao
 * <p>
 * description: BlockingQueue阻塞队列
 *      ArrayBlockingQueue,LinckedBlockingQueue,SynchronousQueue
 *
 *
 */
public class BlockingQueueDemo {
    public static void main(String[] args) {
        test4();
    }

    /**
     * ArrayBlockingQueue.add()
     * arrayBlockingQueue.element()，检索队首，判断队首是否有数据，没有则抛出异常
     * 抛出异常
     */
    public static void test1(){
        //数组单项队列
        ArrayBlockingQueue arrayBlockingQueue = new ArrayBlockingQueue<>(2);
        System.out.println(arrayBlockingQueue.add("1"));
        System.out.println(arrayBlockingQueue.add("2"));
        System.out.println(arrayBlockingQueue.element());
//        System.out.println(arrayBlockingQueue.add("3"));
        /**
         * 如果添加的数量超过了容量的大小
         * java.lang.IllegalStateException: Queue full
         */
        System.out.println("----------------------------------");

        System.out.println(arrayBlockingQueue.remove());
        System.out.println(arrayBlockingQueue.remove());
        /**
         * 如果弹出的数量大于所含有的对象的数量
         *  java.util.NoSuchElementException
         */
        System.out.println(arrayBlockingQueue.remove());

    }

    /**
     * arrayBlockingQueue.offer(),如果可以在不超过队列的长度的尾部插入对象，成功返回true，失败则返回Boolean值false
     * arrayBlockingQueue.poll(),检索并删除此队列的头部，如果此队列为空，则返回 null。
     * arrayBlockingQueue.peek()，检索队首，判断队首是否有数据，如果有则打印,没有的话则返回null，但不抛出异常
     * 不抛出异常，有返回值
     */
    public static void test2(){
        ArrayBlockingQueue<Object> arrayBlockingQueue = new ArrayBlockingQueue<>(3);
        System.out.println(arrayBlockingQueue.offer("1"));
        System.out.println(arrayBlockingQueue.offer("2"));
        System.out.println(arrayBlockingQueue.offer("3"));
        System.out.println("arrayBlockingQueue.element()" + arrayBlockingQueue.peek());

        //返回false
        System.out.println(arrayBlockingQueue.offer("4"));

        System.out.println(arrayBlockingQueue.poll());
        System.out.println(arrayBlockingQueue.poll());
        System.out.println(arrayBlockingQueue.poll());
        //检索并删除此队列的头部，如果此队列为空，则返回 null 。
        System.out.println(arrayBlockingQueue.poll());
        System.out.println("arrayBlockingQueue.element()" + arrayBlockingQueue.peek());
    }

    /**
     * 等待阻塞（一直等待）
     *    arrayBlockingQueue.put();
     *    arrayBlockingQueue.take();
     */
    public static void test3(){
        ArrayBlockingQueue<Object> arrayBlockingQueue = new ArrayBlockingQueue<>(3);
        try {
            arrayBlockingQueue.put("1");
            arrayBlockingQueue.put("2");
            arrayBlockingQueue.put("3");
            //由于数组的长度固定，所以会处于一直等待的阶段，一直阻塞
//            arrayBlockingQueue.put("4");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        try {
            System.out.println(arrayBlockingQueue.take());
            System.out.println(arrayBlockingQueue.take());
            System.out.println(arrayBlockingQueue.take());
            //由于数组里面没有数据所以就会一直处于等待阶段，一直阻塞
            System.out.println(arrayBlockingQueue.take());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


    }

    /**
     * 等待阻塞（超时退出）
     * arrayBlockingQueue.offer("1",2,TimeUnit.SECONDS);以2为超时时间，TimeUnit为时间单位
     * arrayBlockingQueue.offer("1",2,TimeUnit.SECONDS);以2为超时时间，TimeUnit为时间单位
     */
    public static void test4(){
        ArrayBlockingQueue<Object> arrayBlockingQueue = new ArrayBlockingQueue<>(3);
        try {
            arrayBlockingQueue.offer("1",2,TimeUnit.SECONDS);
            arrayBlockingQueue.offer("2",2,TimeUnit.SECONDS);
            arrayBlockingQueue.offer("3",2,TimeUnit.SECONDS);
            arrayBlockingQueue.offer("4",2,TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
            System.out.println("失败");
        }
        try {
            System.out.println(arrayBlockingQueue.poll(3, TimeUnit.SECONDS));
            System.out.println(arrayBlockingQueue.poll(3, TimeUnit.SECONDS));
            System.out.println(arrayBlockingQueue.poll(3, TimeUnit.SECONDS));
            System.out.println(arrayBlockingQueue.poll(3, TimeUnit.SECONDS));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
