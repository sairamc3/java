# Semaphore

A Semaphore controls the access to a shared resource through the use of *counter*. 

- If `counter > 0` -> Access is allowed
- If `counter = 0` -> Access is Denied

What the counter is counting are *permits* that allow access to the shared resource. Thus, to access a shared resource, a thread must be granted a permit from Semaphore.

1. A thread that wants to access a shared resource, tries to acquire a permit
2. If `count > 0`, then thread acquires a permit, which causes `count--`, count to decrement. 
3. If `count = 0`, the thread will be blocked until the permit can be acquired. 
4. When the thread no longer requires access to the shared resource, it releases the permit. Which will cause the counter to increment
5. If there is another thread waiting for the permit, then that thread will acquire the permit at that time. 

## Constructors

- *Semaphore(int num)*
- *Semaphore(int num, boolean how)*

Here *num* indicates the permit count, the number of threads that can access a shared resource at any one time. If `num=1` then only one thread can access the resource at a time. 

By default, waiting threads are granted permits in an undefined order. By setting *how* to `true`, you can ensure that the waiting threads are granted a permit in the order in which they have requested the access. 

## Methods

- *acquire()*
- *acquire(int num)*
- *release()*
- *release(int num)*

*num* indicates the number of permits.

If the permit is not available, then the invoking thread will be suspended until the permit is available. 

