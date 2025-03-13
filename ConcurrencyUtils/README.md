# Concurrency Utils

> Reference from **Java Complete Reference**

## Packages

* java.util.concurrent
* java.util.concurrent.atomic
* java.util.concurrent.locks

### java.util.concurrent

It defines core features that support alternative to the built-in approaches to synchronization and interthread communication. These include

- Synchronizers
- Executors
- Concurrent Collections
- The Fork/Join Framework

#### Synchronizers

|    |     |
|----|-----|
|Semaphor| Controls access to a shared resource through the user of counter | 
|CountDownLatch| Waits until the specified number of events occured|
|CyclicBarrier| Enables a group of threads to wait at a predefined execution point|
|Exchanger| Exchanges data between two threads|
|Phaser| Synchronizes threads that advance through multiple phases of an operation|

#### Executors

Excectors manage thread execution. At the top of the hirarchy there is **Executor interface**, which is used to initiate a thread. **Executor Service** extends executor and provides methods that manage execution. There are 3 implementations of Executor service.

1. ThreadPoolExecutor
2. ScheduledThreadPoolExecutor
3. ForkJoinPool

*java.util.concurrent* also defines executor utility class, which includes number of static methods that simplify the creation of various executors.

Related to executors are **Future** and **Callable** interfaces. 
**Future** - it contains a value that is returned by a thread after it executes.
**Callable** - defines a thread that returns a value

#### Concurrent Collection

*java.util.concurrent* defines several concurrent collection classes, including **ConcurrentHashMap**, **ConcurrentLinkedQueue**, **CopyOnWriteArrayList**. These classes provide concurrent alternatives to their related classes defined by collection framework.

#### The Fork/Join Framework

It supports parllel programming. It's main classes are
- **ForkJoinTask**
- **ForkJoinPool**
- **RecursiveTask**
- **RecursiveAction**
