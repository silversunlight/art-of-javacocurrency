package chapter8;

import java.util.concurrent.Exchanger;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Exchanger 用于线程之间的数据交换
 * 它提供一个同步点，在这个同步点，两个线程可以交换彼此的数据
 * 如果第一个线程先执行exchange方法，他会一直等待第二个线程也执行
 * exchange方法，当两个线程都到达同步点的时候，这两个线程就可以交换数据
 */
public class ExchangerTest {
    private static final Exchanger<String> exgr = new Exchanger<>();
    private static ExecutorService threadPool = Executors.newFixedThreadPool(2);

    public static void main(String[] args) {
        threadPool.execute(() -> {
            try {
                String A = "银行流水A";
                String B = exgr.exchange(A);
                System.out.println("thread1中 A: "+A
                +"\nthread1中 B:"+B);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        threadPool.execute(() -> {
            try {
                String B = "银行流水B";
                String A = exgr.exchange(B);
                System.out.println("A和B的数据是否一致： " +
                        A.equals(B) + "\nA录入的值是：" + A
                        + "\nB录入的值是：" + B);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        threadPool.shutdown();
    }
}
