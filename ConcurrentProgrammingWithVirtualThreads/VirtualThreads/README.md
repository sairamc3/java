# Introduction to Virtual threads

Why do you need virtual threads?

How reactive programming is working.

The idea of reactive programming is to have one thread and many requests. 
What is the cost of the reactive approach?

## Handling Requests Concurrently

### Handling web request with reactive programming

Giving several requests to one thread. It's about splitting a request into small opertions. 

1. Preparing the request object
```java
Supplier<Request> step1 = 
  () -> {
    HttpRequest request = ...;
    return request;
};
```

2. Sending the request

```java
Function<Request, Response> step2 = 
    request -> {
        var client = ...;
        return client.send(request,...);
    };
```

3. Handle exceptions
```java
Function<Throwable, Response> step3 = 
    throwable -> {...};
```

4. Process the response
```java
Function<Response, ...> step4 = 
    response -> response.body();
```

#### Combining all the small operations into meaningful functionality

```java
CompletableFuture.supplyAsync(
    () -> {
        HttpRequest request = ...;
        return request;
        }
    )
    .thenApply(
        request -> {
            var client = ...;
            return client.send(request, ...);
        }
    )
    .exceptionally(
        throwable -> {...})
    .thenApply(
        respone -> response.body())
    .get();
```

How does `CompletableFuture` know that data is available?

*It registers a handler on the O/S signal. When the signal is triggered, it calls your lamda with the data*

So no thread is ever blocked. **As long as your lambdas are non-blocking**

How many Platform threads do you need for this system to work?
*As few as possible. Some systems are working with a single platform thread. One thread per core works well.*


## Reactive Programming drawbacks
1. Reading and writing this code is hard
2. Writing unit tests is hard
3. Error handling is hard
4. Profiling is impossible
5. Debugging is hard
6. The maintenece cost is very high

## Building a new model of thread

With virtual threads it is fine to write blocking code.

* Reactive programming works with platform threads
* With a lighter model of thread
* You could avoid reactive programming
* And you can Keep your code simple

## Virtual Threads in Action

