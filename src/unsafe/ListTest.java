package unsafe;

import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;

//java.util.ConcurrentModificationException,并发修改异常
public class ListTest {
    public static void main(String[] args) {
        /**并发下arraylist不安全
         *
         *  解决方案：
         *      1.Vector线程安全,注：arrayList是1.2出现，Vactor是1.0出现，Vactor使用的是Synchronized进行加锁，所以效率较低
         *      2.使用Collections里面的synchronizedList方法，相当于是加锁
         *      3.使用JUC下面的CopyONWriter方法，写入时复制，COW 计算机程序设计领域的一种优化策略
         *              多个线程调用list的时候，会创建副本，当进行读取的时候是固定的，写入的时候是在list的副本上进行操作，写入的时候避免直接覆盖原list造成数据问题
         */
        List<String> arrayList = new ArrayList<>();
        List<String> synchronizedList = Collections.synchronizedList(arrayList);
        CopyOnWriteArrayList<String> copyOnWriteArrayList = new CopyOnWriteArrayList<>(arrayList);
        for (int i = 1; i <= 10; i++) {
            new Thread(()->{
                copyOnWriteArrayList.add(UUID.randomUUID().toString().substring(0,5));
                System.out.println(synchronizedList + Thread.currentThread().getName());
            },String.valueOf(i)).start();
        }

    }
}
