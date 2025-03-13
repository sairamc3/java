import java.util.concurrent.CountDownLatch;

class CDLDemo {

    public static void main(String[] args) {
        CountDownLatch latch = new CountDownLatch(5);

        System.out.println("Starting");
        new Thread(new MyThread(latch)).start();

        try{
            latch.await();
        }catch(InterruptedException exe){
            System.out.println(exe);
        }
        System.out.println("Done");
    }
}
class MyThread implements Runnable{
    CountDownLatch latch;
    MyThread(CountDownLatch l){
        latch = l;
    }

    public void run(){
        for(int i=0; i<5; i++){
            System.out.println(i);
            latch.countDown();
        }
    }

}