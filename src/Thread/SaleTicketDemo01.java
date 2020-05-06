package Thread;

/**
 * 2020/4/29
 *
 * @author wuzhanhao
 * <p>
 * description:
 * 基本的买票例子
 * 多线程开发,线程就是一个单独的资源类，没有别的附属操作
 * 1.属性，2.方法
 * <p>
 * 并发，多个线程操作一个对象,把资源类丢入线程
 */
public class SaleTicketDemo01 {
    //多线程操作
    public static void main(String[] args) {
        Ticket ticket = new Ticket();
        //使用lambda表达式
        new Thread(() -> {
            for (int i = 0; i < 60; i++) {
                ticket.sellTicker();
            }
            },"A"
        ).start();
        new Thread(() -> {
            for (int i = 0; i < 60; i++) {
                ticket.sellTicker();
            }
        },"B"
        ).start();
        new Thread(() -> {
            for (int i = 0; i < 60; i++) {
                ticket.sellTicker();
            }
        },"C"
        ).start();
    }
}

//资源类,OOP
class Ticket{
    //（属性，对应的方法）,
    private int number = 50;

    //卖票的方式,synchronized锁对象和方法
    public synchronized void sellTicker(){
        if (number>0){
            System.out.println(Thread.currentThread().getName()+"卖出了"+ (number--) + "张票"+ "+" + "剩余"+number + "张票");
        }
    }
}
