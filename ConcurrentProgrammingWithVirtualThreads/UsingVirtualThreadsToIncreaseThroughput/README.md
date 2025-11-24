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

