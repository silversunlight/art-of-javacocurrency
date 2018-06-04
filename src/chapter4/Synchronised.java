package chapter4;
//通过javap -v Synchronised.Class 命令，可以看对程序执行顺序
public class Synchronised {
    public static void main(String[] args) {
        synchronized (Synchronised.class) {

        }
        m();
    }

    private static synchronized void m() {
    }
}
