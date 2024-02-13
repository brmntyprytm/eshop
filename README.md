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