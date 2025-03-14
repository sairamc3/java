# Exchanger

It's designed to simplify the exchange of data between two threads. The operation of **Exchanger** is simple: it simply waits until it's two separate threads call its *exchange()* method. 

It then exchanges the data supplied by the threads. 

## Example

- One thread might prepare a buffer for receiving information over a network connection. 
- Another thread might fill that buffer with information from the network connection.
- The two threads work together so that each time the a new buffer is needed, an exchange is made. 

> **Exchanger<V>** is a generic class. **V** specifies the type of data being exchanged. 

## Methods

- *V exchange(V objRef)*
- *V exchange(V objRef, long wait, TimeUnit tu)* -> Time out period specified

> the *exchange()* method would not succeed until is has been called on the same **Exchanger** object by two separate threads. 
