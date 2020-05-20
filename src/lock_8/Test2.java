package lock_8;

import java.util.concurrent.TimeUnit;

public class Test2 {
    /**
     * 八锁就是锁的八个问题
     * 3.增加了一个不加锁的方法，执行顺序   普通方法-》加锁方法
     *
     */

    public static void main(String[] args) {

        //创建了两个对象，所以就是两把锁
        Phone2 phone1 = new Phone2();
        Phone2 phone2 = new Phone2();

        //并不是先执行先调用，是因为有锁的存在
        new Thread(() -> {
            phone1.send();
        }, "A").start();

        //延迟
        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        new Thread(() -> {
            phone2.takePhone();
        }, "B").start();
//
//        new Thread(()->{
//            phone2.email();
//        },"C").start();
    }
}

class Phone2 {
    /*
    synchronized锁的对象是方法的调用者
    两个方法用的是同一个锁
     */

    public synchronized void send() {


        System.out.println(Thread.currentThread().getName() + "发送消息");
    }

    public synchronized void takePhone() {
        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName() + "打个电话");
    }

    //不是同步方法，先执行
    public void email() {
        System.out.println(Thread.currentThread().getName() + "这个方法是没有加锁的 + 发送一个email");
    }
}
