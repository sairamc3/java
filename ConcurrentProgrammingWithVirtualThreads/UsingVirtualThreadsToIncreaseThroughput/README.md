# Using Virtual Thread to increase your throughput

## Launching Virtual Threads

### Virtual Threads and Carrier Threads

* A virtual thread is executed on a platform thread
* This platform thread is taken from a special fork/ Join pool
* A carrier thread lives in the special fork/join pool dedicated for the execution of virtual threads

How can it be more efficient than plain concurrent programming?

- Running a task in a virtual thread is actually an overhead
- A platform threads scheduler is preemptive. That means, it can stop the thread before its execution completes, so the cpu can be shared across the threads
- A virtual threads scheduler is non-preemptive
- If you have virtual thread which is executing a very long CPU task, it will stay there forever, until this task is done and will never be interupted. 
- Don't use virtual threads for in-memory computations

- Virtual threads are stored as waitlist.
    - An executor service has only one waitlist
    - A Fork/Join pool has one waitlist per thread
        - If one of the waitlist is empty then the fork/join pool will pull the virtual thread from the other waitlist.


> If the task is to do computational tasks, then use platform threads
> If the task is a blocking task, like waiting for a response over internet, then use virtual thread. 

## Blocking a virtual thread

The example shows creation of multiple threads and blocking operations are done in the threads. It demonstrates how the virtual thread behaviour would be. The tasks were executed by different carrier threads. 

## Continuation

The continuation feature is to be applied when preview feature is enabled in java 21. 

```java
var scope = new ContinuationScope("My Scope");
var continuation = new Continuation(scope, 
                        () -> {
                            System.out.println("Running in a continuation");
                            Continuation.yield(scope);
                            System.out.println("After the call to yield");
                            });
System.out.println("Running in the main method");

// Calling it for the first time
continuation.run();
System.out.println("Back in the main method");

//Calling it for the second time
continuation.run();
System.out.println("Back again in the main method");

```
The behaviour of the application in the previous example.
* The `Continuation` constructors contains two parameters (Scope, Runnable).
* The thread will the executed when `continuation.run()` is called for the first time. 
* It will be paused at the point `Continuation.yield(scope)`
* The main thread part will be executed next
* If the second time calling `continuation.run()`, does not exist, then the remaining part of the code in the thread will not be executed.
* When the second call to `continuation.run()` is called, then the thread will continue to execute from the point where it was paused earlier. 

