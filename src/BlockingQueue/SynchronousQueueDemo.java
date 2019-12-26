package BlockingQueue;

import java.util.concurrent.SynchronousQueue;

/**
 * 同步队列，可以把它想像成长度为1的ArrayBlockingQueue，但是实际上它的容量大小为0，必须有一个take才能put进去。
 * 举例：次线程睡五秒take一个元素，主线程put一个元素但是得一直等5s到take执行后才能顺利进行。
 */
public class SynchronousQueueDemo {
    public static void main(String[] args) throws InterruptedException {
        SynchronousQueue queue = new SynchronousQueue();
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
        queue.put(1);
        System.out.println("put结束");
        System.out.println("主线程顺利执行到此，结束");
    }
}
