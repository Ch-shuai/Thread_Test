package help;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**公平读写锁
 * A ReadWriteLock维护一对关联的locks ，一个用于只读操作，一个用于写入。 read lock可以由多个阅读器线程同时进行
 * ReadWriteLock,读写锁，多线程可以同步读取，但是修改只能单线程修改
 */
public class ReadWriteLockDemo {
    public static void main(String[] args) {

        MyCache2 myCache = new MyCache2();

        //写入
        for (int i = 1; i <=5 ; i++) {
            final int tmp = i;
            new Thread(()->{
                myCache.put(tmp+"",tmp+"");
            },String.valueOf(i)).start();
        }
         //读取
        for (int i = 1; i <=5 ; i++) {
            final int tmp = i;
            new Thread(()->{
                myCache.get(tmp+"");
            },String.valueOf(i)).start();
        }


    }
}

class MyCache{
    public volatile Map<String,Object> map = new HashMap<>();
    //存入
    public void put(String key,Object value){
        System.out.println(Thread.currentThread().getName() + "写入" + key);
        map.put(key,value);
        System.out.println(Thread.currentThread().getName() + "写入OK");
    }

    //获得
    public Object get(String key){
        System.out.println(Thread.currentThread().getName() + "读取" + key);
        Object o = map.get(key);
        System.out.println(Thread.currentThread().getName() + "读取OK");
        return o;
    }
}

//加锁
class MyCache2{
    //定义读写锁,更加细粒度的控制
    private ReentrantReadWriteLock reentrantReadWriteLock = new ReentrantReadWriteLock();
    public volatile Map<String,Object> map = new HashMap<>();

    //存入,存入的时候只有一个线程去写
    public void put(String key,Object value){
        //写锁，加锁
        reentrantReadWriteLock.writeLock().lock();
        try{
            System.out.println(Thread.currentThread().getName() + "写入" + key);
            map.put(key,value);
            System.out.println(Thread.currentThread().getName() + "写入OK");
        }finally {
            reentrantReadWriteLock.writeLock().unlock();
        }
    }

    //获得，获取的时候可以多线程获取
    public void get(String key){
        reentrantReadWriteLock.readLock().lock();
        try{
           System.out.println(Thread.currentThread().getName() + "读取" + key);
           Object o = map.get(key);
           System.out.println(Thread.currentThread().getName() + "读取OK");
       }finally {
           reentrantReadWriteLock.readLock().unlock();
       }
    }
}
