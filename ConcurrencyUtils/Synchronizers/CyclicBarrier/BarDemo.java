import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

class BarDemo {
    public static void main(String[] args) {
        CyclicBarrier cb = new CyclicBarrier(3, new BarAction());

        new Thread(new MyThread(cb, "A")).start();
        new Thread(new MyThread(cb, "B")).start();
        new Thread(new MyThread(cb, "C")).start();

        new Thread(new MyThread(cb, "X")).start();
        new Thread(new MyThread(cb, "Y")).start();
        new Thread(new MyThread(cb, "Z")).start();
    }
}

class MyThread implements Runnable {

    CyclicBarrier cb;
    String name;

    MyThread(CyclicBarrier cb, String name){
        this.cb = cb;
        this.name = name;
    }

    public void run() {
        System.out.println(name);

        try{
            cb.await();
        } catch(BrokenBarrierException | InterruptedException exe){
            System.out.println(exe);
        }

    }
}

class BarAction implements Runnable {
    public void run() { 
        System.out.println("Barrier Reached!");
    }
}