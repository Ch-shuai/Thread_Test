package Lock_8;

import java.util.concurrent.TimeUnit;

/**
 * 一个静态方法，一个普通方法
 */
public class Test4 {

    public static void main(String[] args) {

        //celebration

        //创建了两个对象，所以就是两把锁
        Phone4 phone4 = new Phone4();
        //并不是先执行先调用，是因为有锁的存在
        new Thread(() -> {
            Phone4.send();
        }, "A").start();

        //延迟
        try {
            TimeUnit.SECONDS.sleep(2);
            System.out.println("现在在延迟");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        new Thread(() -> {
            phone4.takePhone();
        }, "B").start();


        new Thread(()->{
            Phone4.email();
        },"C").start();
    }
}


//Phone3唯一一个Class对象，锁的是class文件
class Phone4 {
    /**
     *  synchronized锁的对象是方法的调用者，static修饰的是一个静态方法，类加载就存在，Class模板
     *     两个方法用的是同一个锁，锁的是class
     */

    public static synchronized void send() {
        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName() + "发送消息");
    }

    public synchronized void takePhone() {
        System.out.println(Thread.currentThread().getName() + "打个电话");
    }

    //不是同步方法，先执行
    public static void email() {
        System.out.println(Thread.currentThread().getName() + "这个方法是没有加锁的 + 发送一个email");
    }
}


