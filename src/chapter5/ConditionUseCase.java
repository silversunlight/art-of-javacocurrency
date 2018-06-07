package chapter5;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * condition的使用示例
 */
public class ConditionUseCase {
    Lock lock = new ReentrantLock();
    Condition condition=lock.newCondition();

    public void conditonWait() {
        lock.lock();
        try {
            condition.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }

    public void conditionSignal() {
        lock.lock();
        try {
            condition.signal();
        }finally {
            lock.unlock();
        }
    }
}
