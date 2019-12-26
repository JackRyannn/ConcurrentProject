package BlockingQueue;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * 链表实现的阻塞队列相比于数组实现的阻塞队列最大优势就是插入删除效率高
 * 举例：我们分别进行10次插入，5次删除，1000个循环，比较两种数据结构的速度
 */
public class LinkedBlockingQueueDemo {
    public static void main(String[] args) throws InterruptedException {
        LinkedBlockingQueue<Integer> linkedBlockingQueue = new LinkedBlockingQueue<>();
        ArrayBlockingQueue<Integer> arrayBlockingQueue = new ArrayBlockingQueue<>(500005);
        long before_time_linked = System.currentTimeMillis();
        for (int i = 0; i < 100000; i++) {
            for (int j = 0; j < 10; j++) {
                linkedBlockingQueue.put(1);
            }
            for (int j = 0; j < 5; j++) {
                linkedBlockingQueue.take();
            }
        }
        System.out.println((System.currentTimeMillis()-before_time_linked) + "毫秒");

        long before_time_array = System.currentTimeMillis();
        for (int i = 0; i < 100000; i++) {
            for (int j = 0; j < 10; j++) {
                arrayBlockingQueue.put(1);
            }
            for (int j = 0; j < 5; j++) {
                arrayBlockingQueue.take();
            }
        }
        System.out.println((System.currentTimeMillis()-before_time_array) + "毫秒");


    }
}
