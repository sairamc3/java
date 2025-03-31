# Open/Closed Principle

> **A software artifact should be open for extension but closed for modification**

- In a stable and working application, once you create a class and other parts of the application start using it, any further change in the class can cause the working application to break.
- If you require new features, instead of changing the existing class, you can extend it to adopt the new requirements.
- Since you do not change the old code, your existing functionalities continue to work without any problems, and you can avoid testing them again. Instead you can test the *extended* part only. 

> **A class is closed, since it may be compiled, stored in a library, baselined, and used by client classes. But it is also open, since any new class may use it as parent, adding new features. When the descendant class is defined, there is no need to change the original or to disturb its clients.**

- But inheritence promotes tight coupling. 
- In Programming, we would like to remove these tight couplings. 
- The new proposal uses abstract base classes that use the protocols instead of a superclass to allow different implmentations. 
- These protocols are closed for modification, and they provide another level of abstraction that enables loose coupling. 

## Example  - Student

- Assume that there is a small group of students who take semister exam. 
- You also mention wheather a student belongs to the science department of Arts Department. 
- Assume college has departments
    - **Science**
        - Computer Science
        - Physics
    - **Arts**
        - History
        - English
- Lets start with two methods
    - `displayResult()` -> Prints result with all necessary details of student
    - `evaluateDistinction()` -> Evaluates whether the student qualifies for a distinction certificate
        - **Science** -> above **80**
        - **Arts** -> above **70**

### [Without Open/Close Principle](WithoutOpenClosePrinicple/Client.java)

- If in the future the examining authority changes the distinction criteria, you do not touch the Student Class. So this part is closed for modification. 
- The college authority can introduce a new stream such as **Commerce** and sets a new distinction criteria for this stream. 
- You need to make the same choices again. 
    - You need to modify `evaluateDistinction()` method and add another `if` statement to consider the commerce students. 
- So the class `DistinctionDecider` is not closed for modification. 

### [With Open/Close Principle](WithOpenClosePrinciple/Client.java)

- The `Student` class and `DistinctionDecider` class both are unchanged for any future changes in the distinction criteria. They are closed for modification
- Every participant follows **SRP**
- If there is a new stream **Commerce**, You can create a new class `CommerceStudent`. In this case, you need not touch `ScienceStudent` or `ArtsStudent`
- Similarly, you can add new `CommerceDistinctionDecider` that implements `DistinctionDecider`. In this case, you need not touch `ScienceDistinctionDecider` or `ArtsDistinctionDecider`. 
- Using this approach, we are avoiding the `if-else` chain. 
