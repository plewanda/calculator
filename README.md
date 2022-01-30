# General information
This is my approach to the Proof IT technical assignment problem.

It is written using Java 17 and maven. 
For the list of used libraries see pom.xml.

Maven needs to be in version 3.8.4+ to support Java 17.
Make sure you have both Java and maven installed in needed versions.

To run all the tests, from the main project directory execute:

    $ mvn test

You may also run the app (but it won't do much ;) ) by executing:

    $ mvn spring-boot:run

# Assumptions
 - I am rounding only the final sum of the policy.
 - Based on the expected results from task description I am using HALF_UP method for rounding.