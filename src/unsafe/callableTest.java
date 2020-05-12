package unsafe;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

public class callableTest {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        //由于启动线程
        MyThread thread = new MyThread();
        //FutureTask可用于包装Callable或Runnable对象，public FutureTask(Callable<V> callable){   }
        FutureTask futureTask = new FutureTask<>(thread);
        new Thread(futureTask, "MyThread").start();
        FutureTask<String> RunnableFuture = new FutureTask<>(new MyThreadTwo(),"111");

        new Thread(RunnableFuture).start();
        String s = RunnableFuture.get();
        System.out.println(s);

        try {
            String str = futureTask.get().toString();
            System.out.println("str -----> " + str);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

    }
}

//Callable<V>,表示返回的类型
class MyThread implements Callable<String>{

    @Override
    public String call() throws Exception {
        System.out.println("进入线程");
        return "123";
    }
}

class MyThreadTwo implements Runnable{

    @Override
    public void run() {
        System.out.println("Runnable");
    }
}
