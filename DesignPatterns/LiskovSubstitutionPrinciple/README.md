# Liskov Substitution Principle

> **The *LSV* says that you should be able to substitute a parent (or base) type with a subtype.**

- You can use a derived class instead of its base class without altering the correctness of the program.

## Example

- Online payment portal to pay my electricity bill. 
- Registerd user, when raise a payment request, it shows my previous payments too. 
- You got a new requirement that says you need to support guest users in the future
- You can process the guest user's payment request, but you do not show their last payment details.

### [Without LSV](WithoutLSP/Client.java)

- There is no specialization for Guest user
- The feature of showing the previous transactions is not present here
- Hence if we implment the `Payment` interface which has both the methods defined, then this would create an error for the guest user. 

### [With LSV](WithLSP/Client.java)

- The single interface is divided into two interfaces
  - One for Previous payments
  - Other for current Payment
- Registerd user implments both the interfaces
- Guest user implements only for current payment
- This new structure solves the problem you have faced earlier
- The modfied design confirms to LSP because objects are clearly substitutable and the program works properly
