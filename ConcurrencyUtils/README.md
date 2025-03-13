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
