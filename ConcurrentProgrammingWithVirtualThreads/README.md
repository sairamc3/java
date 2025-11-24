# Concurrent Programming in Java with Virtual Threads

Reactive programming is used to increase the throughput of the application. But it comes with its setbacks. 
With the virtual threads and structured concurrency we can achieve the same throughput much effieciently. 

## Throughput of an application

It depends on what your application is doing. 

**The number of elements you process per second, or millisecond. Your elements can be HTTP requests, DB requests, messages, etc...**

Ractive programming is based on Asynchronous programming. 

What is Asynchronous Programming?

## Synchronous Vs. Asynchronous

* Synchronous means that the code will be executed immedietly. 
* Asynchronous means that the code might execute in the future.

Asynchronous programming is different from the concurrent programming. 
There is no thread management in the Asynchronous programming. 

Like 

```java
var strings = List.of("one", "two", "three");

strings.forEach(System.out::println);
string.sort(Comparator.naturalOrder());
```

## Concurrent Execution

Concurrent is always asynchronous, but asynchronous is not always Concurrent.

Here we are going to use Threads, and Executor service to manage the concurrent programming. 

## Blocking vs Non-Blocking

**Blocking**: Your code is blocking if it blocks the threads that is running it without using your CPU.
How is it possible?
Two ways:

1. Your thread is waiting for soem I/O data
2. Or waiting to execture a synchronized block

Making the non-blocking code is what the reactive programming does. Context switching is costlier. 

## Analyzing the performance of a request

A synchronous http request contains
- Creating the request objects - `100 ns`
- Sending the request - `100 ms`
- you get the response and you are analysing it - `100 ns`

During the Creating the request and processing the response phase, the CPU usage is high. That's good. You are utilizing it. But while sending request, the CPU usage is very low. Around `0.0002%`

How can you fix that? To keep your CPU core busy.

The first solution would be to send your network requests in parrlel. Like each thread sending a request parllely. 

With this approach how many threads do you need to reach 100% usage of the CPU?

There is a factor of 1 million. Between in-memory computation and a request/response cycle time.
So how many threads do you need for one CPU core? **1 million**

## Sending Concurrent Requests

* A java thread is a wrapper on an operating system thread ( Kernel Thread/ Platform thread).
* So the cost of a java thread is boud by the cost of a platform thread. 

### Analyzing the cost of one Thread

#### Platform thread
|||
|-|-|
|Memory| 1 MB|
|Start-up time| 1ms|
|context switching| 100 micro seconds|

it's very expensive. This is why you need to pool them. You cannot have one million of them in a regular machine.

**Cost of 1 million plaform threads**
|||
|-|-|
|Memory| 1 TB|
|Start-up time:| 1,000 s|
|Context switching:| 100s|

How can you fix that?

You need to keep your CPU core busy. Two solutions can come to mind:
1. Give several requests to one thread
2. Build a thread that is lighter than a platform thread

