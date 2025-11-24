package org.example.chapter4;

import java.time.Duration;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.concurrent.locks.ReentrantLock;


public class H_FixingPinningDueToSynchronizaitonUsingEntrantLock {

    static int counter;

    public static void main(String[] args) throws InterruptedException {
        var lock = new ReentrantLock();

        Runnable task1 = () -> {
            System.out.println(Thread.currentThread());
            lock.lock();
            try{
                counter++;
                sleepFor(1, ChronoUnit.MICROS);
            } finally {
                lock.unlock();
            }


            System.out.println(Thread.currentThread());
            lock.lock();
            try{
                counter++;
                sleepFor(1, ChronoUnit.MICROS);
            } finally {
                lock.unlock();
            }

            System.out.println(Thread.currentThread());
            lock.lock();
            try{
                counter++;
                sleepFor(1, ChronoUnit.MICROS);
            } finally {
                lock.unlock();
            }
            System.out.println(Thread.currentThread());
        };

        Runnable task2 = () -> {
            lock.lock();
            try{
                counter++;
                sleepFor(1, ChronoUnit.MICROS);
            } finally {
                lock.unlock();
            }
            lock.lock();
            try{
                counter++;
                sleepFor(1, ChronoUnit.MICROS);
            } finally {
                lock.unlock();
            }
            lock.lock();
            try{
                counter++;
                sleepFor(1, ChronoUnit.MICROS);
            } finally {
                lock.unlock();
            }

        };

        int nThreads = 20_000;

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
