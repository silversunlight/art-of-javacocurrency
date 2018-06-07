package chapter5;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 有界队列是一种特殊的队列，当队列已满的时候，队列的插入操作会阻塞线程，直到队列
 * 出现空位，当队列为空时，队列的获取操作会阻塞获取线程，直到队列中有新增元素
 * <p>
 * condition的使用，进一步
 */
public class BoundedQueue<T> {
    private Object[] items;
    //添加的下标，删除的下标，和数组的当前数量
    private int addIndex, removeIndex, count;
    private Lock lock = new ReentrantLock();
    private Condition notEmpty = lock.newCondition();
    private Condition notFull = lock.newCondition();

    public BoundedQueue(int size) {
        items = new Object[size];
    }

    //添加一个元素，如果数组满，则添加线程进入等待状态，直到有“空位”
    public void add(T t) throws InterruptedException {
        lock.lock();
        try {
            while (count == items.length)
                notFull.await();
                items[addIndex]=t;
                if (++addIndex==items.length)
                    addIndex=0;
                ++count;
                notEmpty.signal();
        }finally {
            lock.unlock();
        }
    }

    //由头部删除一个元素，如果数组空，则删除线程进入等待状态，直到有添加新元素
    @SuppressWarnings("unchecked")
    public T removed() throws InterruptedException {
        lock.lock();
        try {
            while (count==0)
                notEmpty.await();
            Object x = items[removeIndex];
            if (++removeIndex==items.length)
                removeIndex=0;
            --count;
            notFull.signal();
            return (T)x;
        }finally {
            lock.unlock();
        }
    }
}
