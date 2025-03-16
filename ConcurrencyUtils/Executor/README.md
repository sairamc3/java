# Executor

**Executor** can be used to initiate and controle the execution of the threads. It's an alternative to managing threads through **Thread** class.

## Interfaces

### Executor

```java
void execute(Runnable thread);
```
`execute()` method starts the specified thread.

### ExecutorService extends Executor

It has methods that help manage and control the execution of threads. Example of a method is

```java
void shutdown();
```
it also defines methods:
- that execute threads that return results
- that executes set of threads
- that determines shutdown status

### ScheduledExecutorService extends ExecutorService

It supports scheduling of threads.

## Classes

- `ThreadPoolExecutor` -> implements `ExecutorService` and provides support for managed pool of threads.
- `ScheduledThreadPoolExecutor` -> implements `SchduledExecutorService` to allow pool of threads to be scheduled.
- `ForkJoinPool` -> implements `ExecutorService` and is used by *Fork/Join Framework*.

> A **Thread Pool** provides a set of threads that are used to executre various tasks. Instead of each task using its own thread, the threads in the pool are used. This reduces the overhead of creating many threads. 

- `Executors` -> It's a utiltiy class, which has static factory methods that can be used to get an `executor`. 
    - `static ExecutorService newCachedThreadPool()` -> Creates a new thread pool that adds threads as needed but reuses the threads if possible. 
    - `static ExecutorService newFixedThreadPool(int numThreads)` -> Creates a thread pool that consists of a specified number of threads.
    - `static ScheduledExecutorService newScheduledThreadPool(int numThreads)` -> Creates a thread pool that supports scheduling with specified number of threads.
