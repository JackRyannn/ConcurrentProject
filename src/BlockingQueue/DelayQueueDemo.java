package BlockingQueue;

import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.DelayQueue;
import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

/**
 * 延迟队列是比较特殊的一种,它对队列元素有要求，元素必须实现Delayed接口。而且队列本身是有序的。
 * 接口方法一getDelay()需要返回剩余过期时间，这个值到0则过期并可以返回。
 * 接口方法二compareTo()就是实现内部排序的，必须让过期元素排在队首才能返回，若队首没过期则返回null
 * 应用场景：定时任务调度
 */
public class DelayQueueDemo {
    public static void main(String[] args) {
        DelayQueue<Item> delayQueue = new DelayQueue<Item>();
        Item item1 = new Item(3,TimeUnit.SECONDS);
        Item item2 = new Item(6,TimeUnit.SECONDS);
        delayQueue.add(item1);
        delayQueue.add(item2);
        while(true){
            Item item = delayQueue.poll();
            if(item==null)
                break;
            System.out.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date(item.time)));
        }
    }

    static class Item implements Delayed {
        private long time;

        public Item(long time, TimeUnit unit) {
            this.time = unit.toMillis(time) + System.currentTimeMillis();
        }

        /**
         * item的剩余到期时间
         * @param unit
         * @return 返回值小于等于0才可以被返回（表示过期）
         */
        @Override
        public long getDelay(TimeUnit unit) {
            return System.currentTimeMillis() - time;
        }

        /**
         * 比较剩余到期时间，越小越靠近队列头部
         * @param o
         * @return
         */
        @Override
        public int compareTo(Delayed o) {
            Item item = (Item) o;
            long diff = this.time - item.time;
            if (diff <= 0) {// 改成>=会造成问题
                return -1;
            } else {
                return 1;
            }
        }
    }
}
