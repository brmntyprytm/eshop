# Advanced Programming: Tutorial

## Bramantyo Priyo Utomo / 2206821563

<details>
<summary><strong>01 - Coding Standards</strong></summary>

### Reflection 1
Honestly, after a not so careful evaluation of my source code, there are a lot of things wrong in there.
For one, there is hardly any error handling of any kind, also many of the arguments inside some functions are straight up
brute force level of coding without any algorithmic touch to them at all, if it works, it works I guess.

While I recognize a lot of things wrong here, I also think that it's really not that bad. Code cleanliness for example,
I think that my code is easy to understand at first glance, I also put in the effort to make the format of my code consistent throughout the source code.

### Reflection 2
1.  I feel as though more tests are needed to ensure absolute confidence, but it is good enough as it is in my opinion. About how many unit tests do we need? That actually depends on how many features you are implementing in your software, in this case deleting and editing
are our only features as of yet, so there's only so much we could do about the unit tests. Code coverage refers to the measurement to which how much of a program is covered by the test suites,
it would definitely help if I am skilled enough to know what I am missing out on, although for now, this will have to do. I think that
even if a program has 100% code coverage, it doesn't magically mean that the whole code is bug-free, in other words, like everything else, 
somethings are just out of our reach, no matter how tech savvy you are, there are going to be times where tests just can't cover everything.


2. If the new functional test suite closely resembles the existing ones, there's a risk of introducing code duplication. This can make the codebase 
harder to maintain and increase the likelihood of inconsistencies if changes are needed.  Implement robust error handling and reporting mechanisms to provide meaningful feedback when tests fail.

I feel like a real programmer after writing some unit and functional tests, and I actually learned that by printing out what my code does
during testing makes it so that I know what it is they're doing at that moment, like I just had a eureka moment.

</details>

<details>
<summary><strong>02 - CI/CD</strong></summary>

### Reflection
1. There were A TON of code quality issues that I ran into during the development process of the second module, I knew my code was a mess, but it became very apparent after installing Sonarcloud, I could see
on the dashboard how many things were going wrong. To list things off, I may have deleted some assertions of my unit tests, had I not been notified of this, the tests would have been useless. Not to mention the 
one (1) bug which I didn't even know could even happen. There might not be any strategies involved in my bug fixing, as soon as I see a problem, I have to fix it (most of the time). It all comes down to "red bad green good",
which is to say, squash bugs as soon as I see it.

2. Quite a few things happening behind the GitHub workflows of the app, there is the Sonarcloud thing that I mentioned before, it's the CI that I choose to pick for automated testing, this ensures that anything I change
doesn't make it explode. Integration with SonarCloud performs static code analysis, identifying code smells, bugs, security vulnerabilities, and other issues early in the development process.
The CD side of things, I chose to go with Koyeb, that does its job by monitoring the performance and health of the deployed application, enabling quick detection and response to issues in production.
There might be room for improvements on the optimization side of things, though I am happy with the way it actually works and not you know, not work.



</details>

<details>
<summary><strong>03 - OO Principles</strong></summary>

### Reflection
1. - Single Responsibility Principle (SRP):
   Each class (ProductRepository and CarRepository) has a single responsibility related to managing data for their respective entities (Product and Car). They handle operations such as creation, deletion, and retrieval of data related to their entity.
   - Open/Closed Principle (OCP):
   The code is designed in a way that allows for extension without modification. For example, if you need to add new functionality to the repositories, you can do so by adding new methods or extending the interfaces, without modifying the existing code.
   - Liskov Substitution Principle (LSP):
   It adheres to the principle by ensuring that subclasses can be used interchangeably with their parent classes. For instance, if you have subclasses of Product or Car, they should be able to be used wherever a Product or Car is expected.
   - Interface Segregation Principle (ISP):
   Each repository interface (ProductRepository and CarRepository) defines a set of methods specific to the data entity it manages. This promotes smaller, more focused interfaces that clients can depend on without being forced to implement unnecessary methods.
   - Dependency Inversion Principle (DIP):
   The code uses dependency injection to invert the dependencies between higher-level and lower-level modules. For example, the ProductService and CarService classes depend on abstractions (ProductRepository and CarRepository interfaces) rather than concrete implementations 
   (ProductRepositoryImpl and CarRepositoryImpl). This promotes decoupling and allows for easier substitution of implementations.


2. - After applying SOLID, the code is made easier to maintain, test, and extend on, leading to a more robust and scalable codebase.
   - Product and Car interfaces are made to allow you to treat different content types uniformly and add types without modifying existing code.
   

3. - Without adhering to SOLID principles, your code tends to become tightly coupled and less flexible. Making changes or adding new features becomes challenging as modifications in one part of the codebase may inadvertently affect other parts.
   - Testing becomes more cumbersome as components are tightly coupled, leading to difficulties in isolating and mocking dependencies.
   - Lack of clear interfaces and responsibilities can hinder the addition of new features or the adaptation to changing requirements, resulting in a brittle and unmaintainable codebase.
</details> 

<details>
<summary><strong>04 - TDD & Refactoring</strong></summary>

### Reflection 1
1. Did you make the tests before the code?
   - Yes, the tests were created before writing the code. Test cases such as `testCreateOrderEmptyProduct`, `testSetStatusToInvalidStatus`, `testCreateOrderIfAlreadyExists`, etc. Were written to define the expected behavior of the Order class.
2. Did you run the tests for the right reasons?
   - The tests were run to verify that the implemented code functions correctly according to the defined requirements and that it handles edge cases appropriately. For example, ensuring that creating an order with empty products throws an exception (`testCreateOrderEmptyProduct`) and setting an invalid status throws an exception (`testSetStatusToInvalidStatus`).
3. Did you actually write the right code?
   - The written code addresses the requirements specified by the tests. However, there was an oversight in the implementation of the `createOrder` method in the `OrderServiceImpl` class, which caused the tests to fail. This indicates a need for more careful implementation.
4. Did you refactor properly once the tests passed?
   - Yes, the code was refactored after passing tests to improve code quality, readability, and maintainability. In this case, refactoring might involve improving the error handling mechanism in the createOrder method and ensuring consistency in naming conventions.

### Reflection 2

1. Fast: Each test case focuses on a specific behavior and completes its execution swiftly.
2. Isolated: Each test sets up its own data and verifies a specific behavior without interference from other tests.
3. Repeatable: Running the tests multiple times should yield the same results, provided the code and test environment remain unchanged.
4. Self-Validating: They use assertion methods like assertEquals and assertThrows to automatically determine the test outcome. The tests do not require manual inspection to determine success or failure.
5. Timely: tests are already written before the code they are testing to drive the development process
</details>