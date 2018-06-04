package chapter4;

import java.util.concurrent.TimeUnit;

//ThreadLocal 的使用
public class Profier {
    //第一次get()方法调用时会初始化（如果set方法没有被调用），每个线程调用一次
    private static final ThreadLocal<Long> TIME_THREADlOCAL=new ThreadLocal<>(){
        protected Long initialValue() {
            return System.currentTimeMillis();
        }
    };

    public static final void begin() {
        TIME_THREADlOCAL.set(System.currentTimeMillis());
    }

    public static final long end() {
        return System.currentTimeMillis()-TIME_THREADlOCAL.get();
    }

    public static void main(String[] args) throws InterruptedException {
        Profier.begin();
        TimeUnit.SECONDS.sleep(1);
        System.out.println("Cost: "+Profier.end()+" mills");
    }
}
