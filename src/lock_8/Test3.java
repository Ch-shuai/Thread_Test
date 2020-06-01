package lock_8;

import java.util.concurrent.TimeUnit;

/**
 * 八锁就是锁的八个问题
 * 4.增加两个静态的synchronized方法,只有一个对象，先打印哪个？
 * <p>
 * 由于static这个修饰的方法是和类加载同时进行，所以锁的是这个class模版，
 */


public class Test3 {

    public static void main(String[] args) {

        //celebration

        //创建了两个对象，所以就是两把锁
        Phone3 phone3 = new Phone3();

        //并不是先执行先调用，是因为有锁的存在
        new Thread(() -> {
            Phone3.send();
        }, "A").start();

        //延迟
        try {
            TimeUnit.SECONDS.sleep(2);
            System.out.println("现在在延迟");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        new Thread(() -> {
            Phone3.takePhone();
        }, "B").start();


        new Thread(() -> {
            phone3.email();
        }, "C").start();
    }
}

//Phone3唯一一个Class对象，锁的是class文件
class Phone3 {
    /**
     * synchronized锁的对象是方法的调用者，static修饰的是一个静态方法，类加载就存在，Class模板
     * 两个方法用的是同一个锁，锁的是class
     */

    public static synchronized void takePhone() {
        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName() + "打个电话");
    }


    public static synchronized void send() {
        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName() + "发送消息");
    }


    //不是同步方法，先执行
    public void email() {
        System.out.println(Thread.currentThread().getName() + "这个方法是没有加锁的 + 发送一个email");
    }
}

