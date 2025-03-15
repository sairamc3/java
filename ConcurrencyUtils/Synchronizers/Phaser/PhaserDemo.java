import java.util.concurrent.Phaser;

class PhaserDemo {

    public static void main(String[] args) {
        
        Phaser phaser = new Phaser(1);
        int currPhase;

        System.out.println("Starting");

        new Thread(new MyThread(phaser, "A")).start();
        new Thread(new MyThread(phaser, "B")).start();
        new Thread(new MyThread(phaser, "C")).start();

        // Wait for the threads to complete phase one
        currPhase = phaser.getPhase();
        phaser.arriveAndAwaitAdvance();
        System.out.println("Phase " + currPhase + " Complete");

        // Wait for the threads to complete phase two
        currPhase = phaser.getPhase();
        phaser.arriveAndAwaitAdvance();
        System.out.println("Phase " + currPhase + " Complete");

        // Wait for the threads to complete phase three
        currPhase = phaser.getPhase();
        phaser.arriveAndAwaitAdvance();
        System.out.println("Phase " + currPhase + " Complete");

        // Deregister the main thread
        phaser.arriveAndDeregister();

        if(phaser.isTerminated()) System.out.println("Phaser is terminated");
    }
}

class MyThread implements Runnable {

    Phaser phaser;
    String name;

    MyThread(Phaser phaser, String name){
        this.phaser = phaser;
        this.name = name;
        phaser.register();
    }

    public void run() {

        System.out.println("Thread " + name + " - Beginning phase one");
        phaser.arriveAndAwaitAdvance(); // Signal arrival

        // Pause a bit to avoid jumbled outpu
        try {
            Thread.sleep(100);
        } catch (InterruptedException exe){
            System.out.println(exe);
        }

        System.out.println("Thread " + name + " - Beginning phase two");
        phaser.arriveAndAwaitAdvance(); // Signal arrival

        // Pause a bit to avoid jumbled outpu
        try {
            Thread.sleep(100);
        } catch (InterruptedException exe){
            System.out.println(exe);
        }

        System.out.println("Thread " + name + " - Beginning phase three");
        phaser.arriveAndDeregister(); // Signal arrival and deregister


    }


}