package org.example.chapter4;

import java.time.Duration;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;


public class G_PinningVirtualThreadDueToSynchronizaiton {

    static int counter;

    public static void main(String[] args) throws InterruptedException {
        var lock = new Object();

        Runnable task1 = () -> {
            System.out.println(Thread.currentThread());
            synchronized (lock){
                counter++;
                sleepFor(1, ChronoUnit.MICROS);
            }
            System.out.println(Thread.currentThread());
            synchronized (lock){
                counter++;
                sleepFor(1, ChronoUnit.MICROS);
            }
            System.out.println(Thread.currentThread());
            synchronized (lock){
                counter++;
                sleepFor(1, ChronoUnit.MICROS);
            }
            System.out.println(Thread.currentThread());
        };

        Runnable task2 = () -> {
            synchronized (lock){
                counter++;
                sleepFor(1, ChronoUnit.MICROS);
            }
            synchronized (lock){
                counter++;
                sleepFor(1, ChronoUnit.MICROS);
            }
            synchronized (lock){
                counter++;
                sleepFor(1, ChronoUnit.MICROS);
            }

        };

        int nThreads = 10;

        var threads = new ArrayList<Thread>();

        for(int index=0; index < nThreads; index ++){
            var thread = index == 0 ?
                    Thread.ofVirtual().unstarted(task1):
                    Thread.ofVirtual().unstarted(task2);

            threads.add(thread);
        }

        threads.forEach(Thread::start);

        for(Thread thread: threads) thread.join();

        System.out.println("# threads = " + nThreads);
        System.out.println("counter = " + counter);


    }

    private static void sleepFor(int time, ChronoUnit unit){
        try{
            Thread.sleep(Duration.of(time, unit));
        } catch (InterruptedException ex){
            throw new RuntimeException(ex);
        }
    }
}
