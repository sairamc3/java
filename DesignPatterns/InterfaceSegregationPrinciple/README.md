# Interface Segregation Principle

You often see a fat interface that contains many methods. A class that implements the interface may not need all the methods. 

So why does the interface contain all these methods? 

One possible answer is to support the implementing classes of this interface. This is the area that **Interface Segregation Principle** focuses on.

The idea is *a client should not depend on the method that it does not use*. 
