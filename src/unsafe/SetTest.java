package unsafe;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.CopyOnWriteArraySet;

/**
 * java.util.ConcurrentModificationException    并发修改异常
 *  1.        Set<String> synchronizedSet = Collections.synchronizedSet(hashSet);
 *  2.         ConpyOnWriterSet.synchronizedSet()
 *  HashSet本质是HashMap,HashSet.set --->HashMap.put
 */
public class SetTest {
    public static void main(String[] args) {
        Set<String> hashSet = new HashSet<>();
        CopyOnWriteArraySet copyOnWriteArraySet = new CopyOnWriteArraySet(hashSet);
        for (int i = 1; i <= 10; i++) {
            new Thread(()->{
                hashSet.add(UUID.randomUUID().toString().substring(0,5));
                System.out.println(hashSet);
            },String.valueOf(i)).start();
        }
    }
}
