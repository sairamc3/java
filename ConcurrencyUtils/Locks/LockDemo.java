import java.util.concurrent.*;
import java.util.concurrent.locks.ReentrantLock;

class LockDemo {

    public static void main(String[] args) {

        ReentrantLock lock = new ReentrantLock();

        new Thread(new MyThread(lock, "A")).start();
        new Thread(new MyThread(lock, "B")).start();

    }
}

class Shared {
    static int count = 0;
}

class MyThread implements Runnable {

    ReentrantLock lock;
    String name;

    MyThread(ReentrantLock lock, String name) {
        this.lock = lock;
        this.name = name;
    }

    public void run() {
        System.out.println("Starting " + name);

        try {

            System.out.println(name + " is waiting to lock count");
            lock.lock();

            System.out.println(name + " is locking the count");

            Shared.count++;

            System.out.println(name + " : " + Shared.count);

            System.out.println(name + " is sleeping");

            Thread.sleep(1000);

        } catch (InterruptedException ex) {
            System.out.println(ex);
        } finally {
            System.out.println(name + " is unlocking count");
            lock.unlock();
        }
    }

}