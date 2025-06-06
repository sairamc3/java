# Phaser

It enables the synchronization of threads that represent one or more phases of activity.

## Example

Suppose, you might have threads that implement three phases of an order-processing application.

- **Phase 1:** Separate threads are used to validate
    - Customer Information
    - Check Inventory
    - Confirm Pricing
- **Phase 2:** Two threads that compute
    - Shipping costs
    - All applicable taxes
- **Phase 3:** Two therads confirms
    - Payment
    - Determine estimated shipping time

> **Phaser** works a bit like **CyclicBarrier**, except that it supports multiple phases.

- **Phaser** lets you define a synchronization Object that waits until a specific phase has completed. 
- It then advances to next phase, again waiting until the phase concludes.

## Constructors

- *Phaser()* - registration count is 0
- *Phaser(int numParties)* - registraion count is *numParties*

## Steps to use

- Create a new instance of **Phaser**
- Register one or more parties with **Phaser**. 
    - Either by calling *register()* or 
    - by specifying the *numParties* in the constructor.
- For each registered party, have the phaser wait until all the registered complete a phase.
    - A party signals this, by calling one of the methods such as 
        - *arrive()*
        - *arriveAndAwaitAdvance()*
- After all the parties have arrived, the **Phase is complete**, and the **Phaser** can move to next phase or terminate.

## Methods

- *int register()* -> It registers parites, after Phaser is constructed. It returns phase number of the phase to which it is registered
- *int arrive()* -> To signal a party has completed a phase. 
    - Returns the current phase number. 
    - If the phaser has been terminated it returns -ve value. 
    - This method does not suspend execution of the calling thread. i.e., it does not wait for the phase to be completed. 
- *int arriveAndAwaitAdvance()* -> To indicate the completion of the phase and then wait until all other parties have also completed the task
- *int arriveAndDeregister()* -> Arrive and then deregister itself. It does not wait until the phase is complete.
- *final int getPhase()* -> To obtain current phase number.

## Coding explination

### [PhaserDemo.java](Demo1/PhaserDemo.java)

- The **Phaser** registered 4 parties(Threads)
    1. main thread (through constructor)
    2. A (through *register()* in constructor of **MyThread**)
    3. B
    4. C
- There are 3 phases
    - Main thread `phaser.arriveAndAwaitAdvance()` 3 times
- Each time the phase will be paused until all the threads A,B,C arrives.
- The three threads **Derigister** and then in the last main thread Derigsters, which terminates the **Phaser**

### [PhaserDemo2.java](Demo2/PhaserDemo2.java)

It's possible to take control of what happens when a phase advance occurs. 

To do this you much `@Override` method `onAdvance()`. This method is called by the run time when a phaser advances from one phase to next. 

```java
protected boolean onAdvance(int phase, int numParties)
```
- `phase` will contain the current phase number
- `numParties` will contain no of registered parties

> To terminate the phaser, `onAdvance()` should return `true`. To keep phaser alive, it should return `false`

#### Code 

- The **MyPhaser** is configured with 4 phases and registered 4 parties.
- The threads and the main class will `arriveAndAwaitAdvance` until the phaser terminates. i.e., until the specified no of phases has reached.

```java
if(p == numPhases || regParties == 0) return true;
```
- If the current phase equal to the number of configured phases, or if the registered parties are zero then the `onAdvance` method would return `true`. Which would terminate the phaser. 
