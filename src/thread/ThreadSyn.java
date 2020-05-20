package thread;
/**
 * 2020/4/29
 *
 * @author wuzhanhao
 * <p>
 * description:
 *
 *      线程之间的通信问题：生产者和消费者，通知唤醒
 */
public class ThreadSyn {
    public static void main(String[] args) {
        Data data = new Data();

        //A线程+1
        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                try {
                    data.increment();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "A").start();
        //B线程-1
        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                try {
                    data.decrement();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "B").start();
        //A线程+1
        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                try {
                    data.increment();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "C").start();
        //B线程-1
        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                try {
                    data.decrement();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "D").start();
    }
}

//等待，业务，通知
class Data{
    private int num = 0;

    //+1
    public synchronized void increment() throws InterruptedException {
        while (num != 0){
            this.wait();
        }
        num ++;
        System.out.println(Thread.currentThread().getName() + "num的数量=>" + num);
        this.notifyAll();
    }

    //-1
    public synchronized void decrement() throws InterruptedException {
        while (num==0){
            this.wait();
        }
        num--;
        System.out.println(Thread.currentThread().getName() + "num的数量->" + num);
        this.notifyAll();
    }
}
