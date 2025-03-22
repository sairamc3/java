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

