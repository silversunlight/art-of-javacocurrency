package chapter4;

import java.util.concurrent.TimeUnit;
//线程优雅中断，通过interrupt或者中断标识位
public class ShutDown {
    public static void main(String[] args) throws InterruptedException {
        Runner one=new Runner();
        Thread countThread = new Thread(one, "CountThread");
        countThread.start();
        //睡眠一秒，main线程对CountThread进行中断，使CountThread能够
        //感知中断而结束
        TimeUnit.SECONDS.sleep(1);
        countThread.interrupt();
        Runner two=new Runner();
        countThread = new Thread(two, "CountThread");
        countThread.start();
        //睡眠一秒，main线程对Runner two进行取消，使CountThread能够感知on
        //为false而结束
        TimeUnit.SECONDS.sleep(1);
        two.cancel();
    }
    private static class Runner implements Runnable{
        private long i;
        private volatile boolean on=true;
        @Override
        public void run() {
            while (on && !Thread.currentThread().isInterrupted()) {
                i++;
            }
            System.out.println("Count i= "+i);
        }

        public void cancel() {
            on=false;
        }
    }
}
