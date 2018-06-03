package chapter4;

public class Daemon {
    public static void main(String[] args) {

    }

    static class DaemonRunner implements Runnable{
        @Override
        public void run() {
            try {
                SleepUtils.second(10L);
                //Daemon线程的finally块并不会执行，不能依靠finally块清理
            }finally {
                System.out.println("DaemonThread finally run");
            }
        }
    }
}
