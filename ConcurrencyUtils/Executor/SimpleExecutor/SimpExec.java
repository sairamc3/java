import java.util.concurrent.*;

class SimpleExec {

    public static void main(String[] args) {

        CountDownLatch cd1 = new CountDownLatch(5);
        CountDownLatch cd2 = new CountDownLatch(5);
        CountDownLatch cd3 = new CountDownLatch(5);
        CountDownLatch cd4 = new CountDownLatch(5);

        ExecutorService ex = Executors.newFixedThreadPool(2);

        System.out.println("Starting");

        try {
            ex.execute(new MyThread(cd1, "A"));
            cd1.await();
            ex.execute(new MyThread(cd2, "B"));
            cd2.await();
            ex.execute(new MyThread(cd3, "C"));
            cd3.await();
            ex.execute(new MyThread(cd4, "D"));
            cd4.await();

        } catch (InterruptedException exe) {
            System.out.println(exe);
        }

        ex.shutdown();

        System.out.println("Done");
    }
}

class MyThread implements Runnable {

    String name;
    CountDownLatch latch;

    MyThread(CountDownLatch latch, String name) {
        this.name = name;
        this.latch = latch;
    }

    public void run() {

        for (int i = 0; i < 5; i++) {
            System.out.println(name + ": " + i);
            latch.countDown();
        }
    }

}