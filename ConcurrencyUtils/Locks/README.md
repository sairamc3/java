# Locks

These are objects which offer an alternative to using `synchronized` to control the access to shared resource. 

Here is how a lock works.

- Before accessing the shared resource, a lock that protects that resource is required. 
- When access to the resource is complete, the lock is released.
- When second thread attempts to acquire the lock, when it's in use by first thread, the _second thread will suspend_ until the lock is released.

## Methods

|Method | Description|
|-------|------------|
|*void lock()*| Waits until the invoking lock can be acquired|
|*void lockInterruptibly()*| Waits until the invoking lock can be acquired unless interrupted|
|*Condition newCondition()*| Returns a **Condition** object that is associated with the invoking lock. Using a **Condition**, you gain detailed control of lock through methods such as `await()` and `signal()`, which provide functionality similar to `Object.wait()` and `Object.notify()`|
|*boolean tryLock()*| Attempts to acquire lock. It will not wait if the lock is unavailable. Returns `true` if the lock has been quired, returns `false` otherwise|
|*boolean tryLock(long wait, TimeUnit tu)*| Attempts to acquire lock for the period specified in the paramters|
|*void unlock()*| Releases the lock|


## ReentrantLock

- It's an implementation of lock
- It can be repeatedly entered by the thread that currently holds the lock. 
- In case of thread reentering a lock, all calles to `lock()` must be offset by equal number of `unlock()`
- Otherwise, a thread seeking to acquire a lock will suspend until the lock is not in use. 
