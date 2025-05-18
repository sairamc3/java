# Dependency Inversion Principle

The DIP covers two important things:

1. A high-level concrete class should not depend on a low-level concrete class. Instead, both should depend on abstractions. 
    - If the low-level class changes, the high-level class needs to adjust to the change; otherwise, the application breaks. 
    - It means, you should avoid creating a concrete low-level class inside a high-level class.
    - Instead you should use abstract classes or interfaces. 
    - As a result you can remove tight coupling between classes
2. Abstractions should not depend on details. Instead, the details should depend upon abstractions.
    - If an interface needs to change to support one of the clients, other clients can be impacted due to the change. 
    - No client likes to see such an application

So, in your application, if high-level modules are independent of low-level modules, you can resuse them easily. 

## Exmaple

The example uses a business case where the user can use the employee ID to be saved to the DB. Here the high-level class is `UserInterface` and the low-level class is `OracleDatabase`. 

### Without Dependency Inversiojn Principle

- high-level class(UserInterface) tightly coupled and dependent on the low-level class (OracleDatabase).

### With Dependency Inversion Principle

- A new interface has been created and has been implemented by low-level class
    - interface `Database`
        - class `OracleDatabase` implements `Database`
        - class `MySqlDatabase` implements `Database`
- Instead of instantiating the `OracleDatabase` in the constructor of `UserInterface`, it takes the parameter as a argument of the constructor.- Because of this `UserInterface` is loosly coupled with the `OracleDatabase`
- It can be easily swapped with `MySqlDatabase` without touching the `UserInterface` class.

Please go through the example for the further details. 


