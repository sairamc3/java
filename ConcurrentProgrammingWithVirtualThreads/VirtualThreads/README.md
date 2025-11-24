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


