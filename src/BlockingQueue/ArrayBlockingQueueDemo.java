package BlockingQueue;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * 阻塞队列最主要的特点是阻塞，当队列没元素时获取会阻塞，当队列元素满了添加会阻塞。
 * 举例：这里在队列为空时获取，结果应当是阻塞，我们用poll来获取元素并设置3秒的时限，并打印出前后时间证明确实阻塞了（虽然肉眼可见）。
 */
public class ArrayBlockingQueueDemo {
    final static int TRY_TIME = 3;
    public static void main(String[] args) throws InterruptedException {
        BlockingQueue<Integer> blockingQueue = new ArrayBlockingQueue<Integer>(5);
        long beforeTime = System.currentTimeMillis();
        blockingQueue.poll(TRY_TIME, TimeUnit.SECONDS);
        System.out.println((System.currentTimeMillis()-beforeTime)/1000+"秒");

    }
}
