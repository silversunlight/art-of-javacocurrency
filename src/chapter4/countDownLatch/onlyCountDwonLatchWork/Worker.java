package chapter4.countDownLatch.onlyCountDwonLatchWork;

import java.util.concurrent.CountDownLatch;

public class Worker extends Thread{
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
            System.out.println("第一阶段工作完成");
            countDownLatch.countDown();
            //第二阶段工作都需要两秒
            Thread.sleep(2000);
            System.out.println(name+" 第二阶段工作完成");
            System.out.println(name+" 工作完成，耗时："+(time+2000));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
