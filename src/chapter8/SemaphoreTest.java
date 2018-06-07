package chapter8;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/**
 * semaphore 的acquire() 方法，可以获取一个许可证
 * release() 方法 ，释放一个许可证
 */
public class SemaphoreTest {
    private static final int THREAD_COUTNT = 30;
    private static ExecutorService threadPool = Executors.newFixedThreadPool(THREAD_COUTNT);

    private static Semaphore s = new Semaphore(10);

    public static void main(String[] args) {
        for (int i = 0; i < THREAD_COUTNT; i++) {
            threadPool.execute(() -> {
                try {
                    s.acquire();
                    System.out.println("save data");
                    s.release();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        }
        threadPool.shutdown();
    }
}
