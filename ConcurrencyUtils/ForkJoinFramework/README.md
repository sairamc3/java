# Parllel Programming with Fork/Join Framework

The Fork/Join framework enchances multithreaded programming in two important ways.
1. It simplifies the creation and use of multiple threads
2. It automatically makes use of multiple processors. By using this framework you enable your application to automatically scale to make use of number of available processors.

## Difference between traditional multithreading and Parllel Programming

### Multithreading
- In the past we had a single CPU and multithreading is primarily used to take advantage of idle time.
- One thread can execute while the other thread is waiting
- This type of multithreading is typically supported by an object of type **Thread**
- It's not optimized for situations in which two or more CPUs are available(multicore computers). 

### Parllel Programming

- With two or more CPUs, it is possible to execute portions of the program simultaneously, with each part executing on its own CPU.
- It can be used in functions like sorting, transaforming or searching a long array. 
- In many cases, these type of operations can be broken down into smaller pieces(each acting on portion of array)

## Classes

|      |      |
|------|------|
|`ForkJoinTask<V>`| An abstract class that defines a task|
|`ForkJoinPool`| Manages execution of `ForkJoinTask`s|
|`RecursiveAction`| A subclass of `ForkJoinTask` for the task that do not return values|
|`RecursiveTask<V>`| A subclass of `ForkJoinTask` for tasks that return values|

### `ForkJoinTask<V>`

- `V` specifies the result type of the task.
- `ForkJoinTask` differs from `Thread`, in that `ForkJoinTask` represent lightweight abstraction of a task, rather than a thread of execution.
- `ForkJoinTask`s are executed by Threads managed by a thread pool of type `ForkJoinPool`.
- This mechanism allows a large number tasks to be managed by a small number of actual threads. 
- Thus `ForkJoinTask`s are very efficient when compared to threads. 

#### Methods

- `final ForkJoinTask<V> fork()` - This method submits the invoking task for asynchronous execution of the invoking task. The thread that calls the `fork()` continuous to run. The `fork()` method returns `this` after the task is scheduled for execution.
- `final V join()` - This methods waits unitl the task on which it is called terminates. The result of the task is returned. 

This by the use of `fork()` and `join()`, you can start one or more new tasks and then wait for them to finish. 

- `final V invoke()` - It combines the `fork()` and `join()` operations into single call because it begins a single task and waits for it to end. 
- `static void invokeAll(ForkJoinTask<?> taskA, ForkJoinTask<?> taskB)`
- `static void invokeAll(ForkJoinTask<?> ... taskList)`

### `RecursiveAction`

- A subclass of `ForkJoinTask`
- It encapsulates a task that does not return a result

#### Methods

- `protected abstract void compute()`

> In general, `RecusriveAction` is used to implement a recursive, divide-and-conquer strategy for tasks that don't return results. 

### `RecursiveTask<V>`

- A subclass of `ForkJoinTask`
- It encapsulates a task that returns a result

#### Methods

- `protected abstract V compute()`

> In general, `RecusriveTask<V>` is used to implement a recursive, divide-and-conquer strategy for tasks that return results. 

### `ForkJoinPool`

- The execution of `ForkJoinTask` happens with in `ForkJoinPool`, which also manages the execution of tasks.
- In order to execute `ForkJoinTask`, you must have `ForkJoinPool`.

Ways to create 
1. You can use constructor
2. You can use from *common pool*. It's a static `ForkJoinPool` that is automatically availalble for use. 

#### Constructors

- `ForkJoinPool()` -> Level of parllelism is equual to number of processors available.
- `ForkJoinPool(int pLevel)` -> Level of parllelist should be > 0 and should not be more than the limits of implementation.

> `Level of parllelism` is nothing but number of threads that can run parllelly. It's important to note that level of parllelism does not limit the number of task that can be managed by the pool. 

`ForkJoinPool` can manage many more tasks than its level of parllelism. 

#### Methods

- `<T> T invoke(ForkJoinTask<T> task)` - This method return result, hence the calling thread waits until the `invoke()` returns. 
- `void execute(ForkJoinTask<?> task)` - The calling thread does not wait for its completion.
- `static ForkJoinPool commonPool()` - A refernce to the common pool will be returned. 

There are two ways you can start a `ForkJoinTask` from common pool. 
1. Get the reference of the *common pool* from the `commonPool()` static method and call `invoke()` or `execute()`.
2. From the `ForkJoinTask<V>` itself, if you call the methods, `fork()` or `invoke()`, it will start a task using the *common pool*


- `ForkJoinPool` manages the execution of the threads using an approach called *Work-stealing*
    - Each worker thread maintains a queue of tasks
    - If one threads queue is empty, it will take a task from another thread. 
    - This adds to overall efficiency and helps to maintain balanced load.
- `ForkJoinPool` uses **Deamon threads**
    - A *Deamon thread* is automcatically terminated when all the user threads have terminated.
    - There is no need to explicitely shutdown `ForkJoinPool`.
    - However there is an excpetion for *Common pool*. 
    - The `shutdown()` will not have any effect on *Common Pool*.

- `void execute(ForkJoinTask<?> task)` -> to start a task asynchronously.
- `void execute(Runnable task)` -> to start using traditional threads.

> This is coming from `Executors`. `ForkJoinPool` is one of the **Executors**.

Ref: [Executor Classes](../Executor#classes)
