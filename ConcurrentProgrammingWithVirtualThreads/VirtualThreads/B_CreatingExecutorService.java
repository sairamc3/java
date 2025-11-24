package org.example;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class B_CreatingExecutorService {

    public static void main(String[] args) {

        var set = ConcurrentHashMap.<String>newKeySet();
        Runnable task = () -> set.add(Thread.currentThread().toString());

        int nTasks = 2000;

        try(var executorService = Executors.newVirtualThreadPerTaskExecutor()) {
            for(int index = 0; index < nTasks; index++){
                executorService.submit(task);
            }
        }

        System.out.println("#Threads used = " + set.size());
    }
}
