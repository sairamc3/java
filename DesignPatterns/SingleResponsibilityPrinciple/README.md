# Single Responsibility Principle

If you put too much data or methods that are not related to each other, you end up with bulky class that can create problems in future. 

Suppose you create class with multiple methods that do different things. In such case, even if you make small change in one method, you need to retest the whole class again to ensure the workflow is correct. 

**A class should have only one reason to change**

So, before you make a class, identify the responsibility or purpose of the class. If multiple members help you achieve a single purpose, it's okay to place all the members inside the class. 

**Responsibility is a reason to change**

## Example
 
### [With multiple Responsibility](Multiple/Client.java)

#### Analysis

- You can see that all are different activities.
    - Displaying employee detail
    - Generating employee id
    - Checking seniority level 
- Since I put everything in a single class, I may face problems because of this. 
- Reasons
    - The top management can set different criteria to decide seniority level
    - They can also use a complex algorithm to generate employee id.
- In each case, you need to modify `Employee` class. 

### [With Single Responsibility](Single/Client.java)

- *SRP* does not say that the class should have exactly one method. 
- For example, if the employee class has different methods to show the firstName and lastName, that's fine. These methods are closely related. 
