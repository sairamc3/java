# CyclicBarrier

When a set of two or more threads must wait at  a predetermined execution point until all threads in the set have reached that point, we have **CyclicBarrier** to handle such situation. 

It enables you to define synchronization object that suspends until the specified number of threads has reached a barrier point. 

## Constructor

- *CyclicBarrier(int numThreads)*
- *CyclicBarrier(int numThreads, Runnable action)*

*numThreads* specifies the number of threads that must reach the barrier before the execution continues. 

## Steps
- Create a **CyclicBarrier** Object, with number of threads
- When each thread reaches a barrier, call *await()* on the object. 
- This will pause the execution of the thread until all the other threads also call *await()*.
- Once specified number of threads reaches the barrier, *await()* will return and execution will resume.
- If you have specified an *action*, then that thread is executed. 

## Methods

- *int await()* -> it waits until all the threads reaches the barrier point
- *int await(long wait, TimeUnit tu)* -> it waits for only for the period of time specified by *wait*. 

Both forms return a value that indicats the order that the threads arrive at the barrier point. 
- 1st thread return (no of threads - 1)
- last thread returns zero.

> The precise order in which threads execute may vary.


