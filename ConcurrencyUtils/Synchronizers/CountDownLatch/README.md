# CountDownLatch

Sometimes you want a thread to wait until one or more events have occured. To handle such a situation, the Concurrent api provides **CountDownLatch**. 

A **CountDownLatch** is created with number of events that must occur before the latch is released. Each time an event happens, the count is decremented. 
When count reaches `0`, the latch opens.

## Constructor

*CountDownLatch(int num)*

*num* specifies the number of events that must occur in order for the latch to open. 

## Methods

- *void await()* -> It waits until the **CountDownLatch** reaches 0.
- *boolean await(long wait, TimeUnit tu)* -> It waits until the specified amount of time. Returns `true` if the countDown reaches 0, else `false`.
- *countDown()* -> it's an event




