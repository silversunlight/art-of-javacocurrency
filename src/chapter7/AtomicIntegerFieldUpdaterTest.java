package chapter7;

import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;

/**
 * Atomic包提供三个类进行原子字段更新
 * AtomicIntegerFieldUpdater,AtomicLongFieldUpdater,AtomicStampedReference
 * AtomicStampedReference 可以提供带有版本号的引用类型，可以避免CAS操作出现的
 * ABA问题
 * <p>
 * 想要原子的更新字段分为两步，第一步，因为原子更新字段类都是抽象类，每次使用的时候
 * 必须使用静态方法newUpdate()创建一个更新器，并且需要设置想要更新的类和属性，
 * 第二步，更新类的的字段必须使用public volatile修饰符
 */
public class AtomicIntegerFieldUpdaterTest {
    //创建原子更新器，并设置需要更新的对象类和对象的属性
    private static AtomicIntegerFieldUpdater<User> a =
                    AtomicIntegerFieldUpdater.newUpdater(User.class, "age");

    public static void main(String[] args) {
        //设置年龄10岁
        User conan = new User("conan", 10);
        //涨了一岁，但是依然会输出旧的年龄
        System.out.println(a.getAndIncrement(conan));
        System.out.println(a.get(conan));
    }
    static class User {
        private String name;
        private volatile int age;

        public User(String name, int age) {
            this.name = name;
            this.age = age;
        }

        public String getName() {
            return name;
        }

        public int getAge() {
            return age;
        }
    }
}
