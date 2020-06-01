package lock_8;

import java.util.concurrent.TimeUnit;

/**
 * 八锁就是锁的八个问题
 *  1。标准情况下，两个线程打印的顺序？  发短信-》打电话
 *  2。发送短信延迟四秒，两个线程打印顺序？    发送消息-》打电话
 */

public class Test1 {
    public static void main(String[] args) {
        Phone phone = new Phone();

        //并不是先执行先调用，是因为有锁的存在
        new Thread(()->{
            phone.send();
        },"A").start();

        //延迟
        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        new Thread(()->{
            phone.takePhone();
        },"B").start();

//        new Thread(()->{
//            phone.email();
//        },"C").start();
    }
}

class Phone{
    /*
    synchronized锁的对象是方法的调用者
    两个方法用的是同一个锁
     */

    public synchronized void send(){
        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println(Thread.currentThread().getName() + "发送消息");
    }

    public synchronized void takePhone(){
        System.out.println(Thread.currentThread().getName() + "打个电话");
    }

    public void email(){
        System.out.println(Thread.currentThread().getName() + "这个方法是没有加锁的 + 发送一个email");
    }
}
