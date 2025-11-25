# Structured Concurrency

## Loose threads problem

```java
ExecutorService es = ...;

Future<Text> f1 = es.submit(PageReader::readText);
Future<Link> f2 = es.submit(PageReader::readLinks);

Page page = new Page(
                f1.get(1, TimeUnit.SECONDS),
                f2.get(1, TimeUnit.SECONDS));
```

This is asynchronous concurrent and blocking code.

You are actually blocking 3 threads here. 

1. Your main thread which is calling the `get` method
2. The first thread of `ExecutorService`, that is executing `readText`
3. The second thread from the `ExecutorService`, that is executing `readLinks`

If you run this on virtual thread, that is fine. Because, blocking on a virtual thread is cheap. 

There is another problem in the code. **Lose Threads**

Suppose the web server you are quering is not responding, your get methods should both throw a timeout exception, as long as you call them. 

What happens when the first get call fails?

An exception is thrown, and the second get method is never called. So this second get method, does not timeout. 
If the webserver never responds, then the thread running the second request will never be interrupted. You end up with loose threads in your application.

A loose thread in this `ExecutorService` that you will never be able to recover.

After a while, you will realize that your application is not running as smoothly as it used to be. If you analyze things precisely, you will see that you have threads that are not doing anything, and you will not know why. Then you reboot your application, it fixes the problem and then after several days, these loose threads are there again. 

Structured Concurrency is a tool that is built on top of virtual threads. With it you will be able to use it efficiently, without having to manage them  one by one, that would be tedious. 
