# Atomic Operations

**java.util.concurrent.atomic** offers an alternative to the other synchronization features when reading or writing the value of some types of variables. This package offers methods that get, set, compare the value of a variable in one uninterraptable operation. This means that no lock or other synchronization mechanism is required.

## Classes

- `AtomicInteger`
- `AtomicLong`
- `DoubleAccumulator` 
- `DoubleAdder`
- `LongAccumulator`
- `LongAdder`

*accumulator* classes support a series of user-specified operations. 
*adder* classes maintain a cumulative sum

## Methods

- `get()`
- `set()`
- `compareAndSet()`
- `decrementAndSet()`
- `getAndSet()`

