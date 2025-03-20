import java.util.concurrent.atomic.*;

class AtomicDemo {

    public static void main(String[] args) {

        new Thread(new MyThread("A")).start();
        new Thread(new MyThread("B")).start();
        new Thread(new MyThread("C")).start();
    }
}

class Shared {
    static AtomicInteger ai = new AtomicInteger(0);
}

class MyThread implements Runnable {

    String name;

    MyThread(String name) {
        this.name = name;
    }

    public void run() {

        System.out.println("Strating " + name);

        for (int i = 1; i <= 3; i++) {
            System.out.println(name + " got: " + Shared.ai.getAndSet(i));
        }
    }
}