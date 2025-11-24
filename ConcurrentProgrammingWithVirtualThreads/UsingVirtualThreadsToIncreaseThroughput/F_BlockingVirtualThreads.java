package org.example.chapter4;

import java.time.Duration;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;

public class F_BlockingVirtualThreads {


    public static void main(String[] args) throws InterruptedException{
        Runnable task1 =
                () -> {
                    System.out.println(Thread.currentThread());
                    sleepFor(10, ChronoUnit.MICROS);
                    System.out.println(Thread.currentThread());
                    sleepFor(10, ChronoUnit.MICROS);
                    System.out.println(Thread.currentThread());
                    sleepFor(10, ChronoUnit.MICROS);
                    System.out.println(Thread.currentThread());

                };

        Runnable task2 =
                () -> {
                    sleepFor(10, ChronoUnit.MICROS);
                    sleepFor(10, ChronoUnit.MICROS);
                    sleepFor(10, ChronoUnit.MICROS);
                };

        int nThreads = 1;

        var threads = new ArrayList<Thread>();

        for(int index =0; index < nThreads ; index++){
            var thread = index ==0 ?
                    Thread.ofVirtual().unstarted(task1):
                    Thread.ofVirtual().unstarted(task2);
            threads.add(thread);
        }

        threads.forEach(Thread::start);

        for(Thread thread: threads) thread.join();

    }

    private static void sleepFor(int i, ChronoUnit chronoUnit) {

        try{
            Thread.sleep(Duration.of(i, chronoUnit));
        } catch (InterruptedException ex){
            throw new RuntimeException(ex);
        }
    }
}
