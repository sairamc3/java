import java.util.concurrent.Semaphore;

class Q {
    int n;

    static Semaphore semCon = new Semaphore(1);
    static Semaphore semProd = new Semaphore(1);

    void get() {

        try{
            semCon.acquire();
        } catch (InterruptedException exe){
            System.out.println(exe);
        }

        System.out.println("Got: " + n);

        semCon.release();
    }

    void put(int n) {

        try {
            semProd.acquire();
        } catch(InterruptedException exe){
            System.out.println(exe);
        }

        this.n = n;
        System.out.println("Put: " +n);
        semProd.release();
    }
}

class Producer implements Runnable {

    Q q;

    Producer(Q q){
        this.q = q;
    }

    public void run(){
        for(int i=0; i<20; i++) q.put(i);
    }
}


class Consumer implements Runnable {

    Q q;

    Consumer(Q q){
        this.q = q;
    }

    public void run(){
        for(int i=0; i<20; i++) q.get();
    }
}

class ProdCon {

    public static void main(String[] args){
        Q q = new Q();

        new Thread(new Consumer(q), "Consumer").start();
        new Thread(new Producer(q), "Producer").start();
    }
}