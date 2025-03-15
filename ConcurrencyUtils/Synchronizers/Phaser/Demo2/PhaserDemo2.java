import java.util.concurrent.Phaser;

class PhaserDemo2 {
    public static void main(String[] args) {
        MyPhaser myPhaser = new MyPhaser(1, 4);

        System.out.println("Starting");

        new Thread(new MyThread(myPhaser,"A")).start();
        new Thread(new MyThread(myPhaser,"B")).start();
        new Thread(new MyThread(myPhaser,"C")).start();

        while(!myPhaser.isTerminated()) myPhaser.arriveAndAwaitAdvance();

        System.out.println("The phaser is teminated");
        
    }
}

class MyPhaser extends Phaser {
    int numPhases;

    MyPhaser(int parties, int numPhases){
        super(parties);
        this.numPhases = numPhases - 1;
    }

    @Override
    protected boolean onAdvance(int phase, int regParties) {

        System.out.println("Phase " + phase + " Completed");
        if(phase == numPhases || regParties == 0) return true;
        return false;
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

    public void run(){

        while(!phaser.isTerminated()){
            
            System.out.println("Thread - " + name + " Beginning phase " + phaser.getPhase());

            phaser.arriveAndAwaitAdvance();

            try {
                Thread.sleep(100);
            } catch (InterruptedException exe) {
                System.out.println(exe);
            }
        }
    }
}