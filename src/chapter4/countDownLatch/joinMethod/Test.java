package chapter4.countDownLatch.joinMethod;

public class Test {
    public static void main(String[] args) throws InterruptedException {
        Worker worker0 = new Worker("worker0", (long) (Math.random() * 2000 + 3000));
        Worker worker1 = new Worker("worker1", (long) (Math.random() * 2000 + 3000));
        Worker worker2 = new Worker("worker2", (long) (Math.random() * 2000 + 3000));

        Thread worker0Thread = new Thread(worker0, "worker0");
        Thread worker1Thread = new Thread(worker1, "worker1");
        Thread worker2Thread = new Thread(worker2, "worker2");

        worker0Thread.start();
        worker1Thread.start();

        worker0Thread.join();
        worker1Thread.join();
        System.out.println("准备工作就绪");

        worker2Thread.start();
    }
}
