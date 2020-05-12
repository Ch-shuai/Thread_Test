package unsafe;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

/**
 * java.util.ConcurrentModificationException    并发修改异常
 */
public class MapTest {
    public static void main(String[] args) {
        //map是这样用的吗，      不是，工作中不用hashMap
        // 默认等价于什么？     初始容量 , 加载因子，
        //关于hashMap -->初始容量 , 加载因子，
        Map<String, Object> hashMap = new HashMap<>(16, 0.75f);
        /**
         * 解决线程不安全
         *   Map<String, Object> synchronizedMap = Collections.synchronizedMap(hashMap);
         *         ConcurrentHashMap<String, Object> concurrentHashMap = new ConcurrentHashMap<>(hashMap);
         */


        for (int i = 1; i <= 30; i++) {
            new Thread(() -> {
                hashMap.put(Thread.currentThread().getName(), UUID.randomUUID().toString().substring(0, 5));
                System.out.println(hashMap);
            }, String.valueOf(i)).start();
        }
    }
}
