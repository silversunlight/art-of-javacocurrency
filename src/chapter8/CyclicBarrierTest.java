package chapter8;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * 让一组线程到达同步点的时候被阻塞，直到最后一个线程到达屏障时，
 * 屏障才会开门，所有被屏障拦截的线程才能继续执行
 * 这个程序的输出结果可能是1，2 ，也可能是2，1
 * 因为到达屏障的时候两个线程才会一起执行
 *
 * cyclicBarrier 的默认构造方法是CyclicBarrier(int parties)
 * 其参数表示屏障拦截的线程数量，每个线程用await方法告诉CyclicBarrier
 * 我已经到达了屏障，然后当前线程被阻塞
 */
public class CyclicBarrierTest {
    static CyclicBarrier c = new CyclicBarrier(2);

    public static void main(String[] args) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    c.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (BrokenBarrierException e) {
                    e.printStackTrace();
                }
                System.out.println(1);
            }
        }).start();

        try {
            c.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (BrokenBarrierException e) {
            e.printStackTrace();
        }
        System.out.println(2);
    }
}
