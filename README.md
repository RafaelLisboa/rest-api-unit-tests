<h2> Digital Innovation: Expert class - Development of unit tests to validate a beer inventory management REST API. </h2>

In this live coding, we will learn how to test, unity, a REST API for the management of beer stocks. We will develop unit tests to validate our beer inventory management system, and also present the main concepts and advantages of creating unit tests with JUnit and Mockito. In addition, we will also show how to develop features of our API through the practice of TDD.

During the session, the following topics will be covered:

* Download a project through Git to develop our unit tests.
* Conceptual presentation about tests: the pyramid of the types of tests, and also the importance of each type of test during the development cycle.
* Focus on unit tests: show why it is important to develop these types of tests as part of the software development cycle.
* Main frameworks for unit testing in Java: JUnit, Mockito and Hamcrest.
* Development of unit tests to validate basic functionalities: creation, listing, consultation by name and exclusion of beers.
* TDD: presentation and practical example in 2 important functionalities: increase and decrease in the number of beers in the stock.

To run the project on the terminal, enter the following command:

`` shell script
mvn spring-boot: run
``

To run the test suite developed during live coding, just run the following command:

`` shell script
mvn clean test
``

After executing the command above, just open the following address and view the execution of the project:

``
http: // localhost: 8080 / api / v1 / beers
``

The following prerequisites are necessary for the execution of the project developed during the class:

* Java 14 or higher versions.
* Maven 3.6.3 or higher versions.
* Intellj IDEA Community Edition or your favorite IDE.
* GIT version control installed on your machine.
* A lot of desire to learn and share knowledge :)

Below are some very cool links on topics mentioned during the class:

* [SDKMan! for managing and installing Java and Maven] (https://sdkman.io/)
* [Intellij IDEA Community reference, for download] (https://www.jetbrains.com/idea/download)
* [Intellij command shortcut palette] (https://resources.jetbrains.com/storage/products/intellij-idea/docs/IntelliJIDEA_ReferenceCard.pdf)
* [Spring's official website] (https://spring.io/)
