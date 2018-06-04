package chapter4.countDownLatch.countdownLatch;

import java.util.concurrent.CountDownLatch;

public class Worker extends Thread {
    //工作者名
    private String name;
    //工作时间
    private long time;

    private CountDownLatch countDownLatch;

    public Worker(String name, long time, CountDownLatch countDownLatch) {
        this.name=name;
        this.time=time;
        this.countDownLatch=countDownLatch;
    }


    @Override
    public void run() {
        try {
            System.out.println(name + " 开始工作");
            Thread.sleep(time);
            System.out.println("工作完成，耗时： " + time);
            countDownLatch.countDown();
            System.out.println("countdownLatch.getCount()= "+countDownLatch.getCount());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
