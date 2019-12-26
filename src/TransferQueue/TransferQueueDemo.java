package TransferQueue;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.LinkedTransferQueue;

/**
 * 转移队列，它和SynchronousQueue很像，不同的是它可以用tryTransfer方法来尝试转移元素，所以在操作流程上有所不同
 * 举例：次线程睡五秒take一个元素，主线程循环转移一个元素。
 */
public class TransferQueueDemo {
    public static void main(String[] args) throws InterruptedException {
        CountDownLatch
        LinkedTransferQueue queue = new LinkedTransferQueue();
        System.out.println("放入一个元素");
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    System.out.println("五秒后take一个元素");
                    Thread.sleep(5000);
                    queue.take();
                    System.out.println("take完成");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
        System.out.println("开始put，但因为没有take所以阻塞了");
        while (!queue.tryTransfer(1)) {
            System.out.println("转移失败，下一秒继续尝试");
            Thread.sleep(1000);
        }
        System.out.println("转移结束");
        System.out.println("主线程顺利执行到此，结束");
    }
}
