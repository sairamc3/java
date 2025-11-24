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

## Preventing the blocking of carrier threads with Continuations

* Under the hood, a virtual thread is launched by a Continuation
* When your virtual thread runs a blocking operation this operation calls `Continuation.yield()`
* It movess the virtual thread away from its carrier thread to 'Heap Memory'
* When the data is available for the blocking operation then any one of the carrier threads picks up the virtual thread from the current state. 
* Continuations make it so that your platform threads are never blocked
* All your blocked virtual threads are stored in your heap memory and invoked again when your data is available

## Pinning a Virtual Thread on its Carrier Thread with a native call

* There is one case where you cannot move the virtual thread from the carrier thread to the heap memeory and bring it back.
* If a virtual thread stack can have addresses of elements held in that stack, then you cannot move it. **Your virtual thread is pinned on your platform thread**

### Theory
- When you create an object in java, the actual object will be stored in the heap memory and the stack will contain the reference to that heap address to access the object. 
- There is no way in java, that can have reference, leading to the address in the stack
- You can call some native code from your java code
- Consider the case, where your java code is calling the native code and the native code is playing with the address in the stack, this code is blocking and you executed it in virtual thread, then moving it to the heap memory will break these addresses, may break your application.
- The virtual thread api identifies the native code and pins your virtual thread on your carrier thread and no more moving to the heap when the code is blocked. 
- This might result in poor performance but your application will be stable. 

## Pinning on Synchronization

This is another way in which virtual threads are pinned. When running the synchronized blocks. This can be explained in the code.

G_PinningVirtualThreadDueToSynchronization.java

- This java clearly shows the pinning of the virtual threads to a carrier thread. 
- There are synchronized blocks inside each thread

The result after running the application is:

```bash
/Users/sairamchekuri/.jenv/versions/21.0/bin/java -javaagent:/Applications/IntelliJ IDEA CE.app/Contents/lib/idea_rt.jar=49461:/Applications/IntelliJ IDEA CE.app/Contents/bin -Dfile.encoding=UTF-8 -Dsun.stdout.encoding=UTF-8 -Dsun.stderr.encoding=UTF-8 -classpath /Users/sairamchekuri/workspace/II/VirtualThread/target/classes org.example.chapter4.G_PinningVirtualThreadDueToSynchronizaiton
VirtualThread[#21]/runnable@ForkJoinPool-1-worker-1
VirtualThread[#21]/runnable@ForkJoinPool-1-worker-1
VirtualThread[#21]/runnable@ForkJoinPool-1-worker-1
VirtualThread[#21]/runnable@ForkJoinPool-1-worker-1
# threads = 10
counter = 30

Process finished with exit code 0
```

Notice that all the virtual threads are executed in the same carrier thread.

The fix for solving the pinning is applied in the class

H_FixingPinningDueToSynchronizationUsingEntrantLock.java

- Instead of using synchronized blocks, `ReentrantLock` has been used. 

Look at the result after implementing it. 

```bash
/Users/sairamchekuri/.jenv/versions/21.0/bin/java -javaagent:/Applications/IntelliJ IDEA CE.app/Contents/lib/idea_rt.jar=49466:/Applications/IntelliJ IDEA CE.app/Contents/bin -Dfile.encoding=UTF-8 -Dsun.stdout.encoding=UTF-8 -Dsun.stderr.encoding=UTF-8 -classpath /Users/sairamchekuri/workspace/II/VirtualThread/target/classes org.example.chapter4.H_FixingPinningDueToSynchronizaitonUsingEntrantLock
VirtualThread[#21]/runnable@ForkJoinPool-1-worker-1
VirtualThread[#21]/runnable@ForkJoinPool-1-worker-3
VirtualThread[#21]/runnable@ForkJoinPool-1-worker-3
VirtualThread[#21]/runnable@ForkJoinPool-1-worker-3
# threads = 20000
counter = 60000

Process finished with exit code 0

```

Here the virtual threads are not pinned are getting assigned to different carrier threads. 
