This is a guide on how to run **raft** = RyanAir Automated Functional Test. 


WHY
---
These are tests for Ryanair interview. They are functional and run locally. 


GET
---
Get from Github: 
```
https://github.com/sikorka/raft
```


ENV
---
Tested on Mac OSX El Capitan so if you have it that's great! 
(It might work on other platforms but was not tested with any.)


Anyhow here is what you need on Mac:

 - setup browsers locally
 - setup Java locally
 - setup Maven locally



Used technologies / tools: 

- Mac OSX El Capitan
- FF 46.0.1, Chrome 51.0 (Safari 10.0 won't work, FF > 46.0.1 neither)
- Java 1.8
- Maven 3.3.9
- JUnit 4.11
- Selenium 2.53.1
- Spock
- Hamcrest


RUN
---
Run using
```
mvn clean test -Dbrowser=ff
```

Enjoy!


REPORT
------

See report locally [here](target/surefire-reports/). 


DOC
---

Just Java docs [here](doc/index.html).


TODO
----

This project is *somewhat* not finished. 

Define *somewhat*: 

- Cucumber tests
- Allure reports

then: 

- better date selection (fix)
- already booked error support (fix)

and perhaps:

- Chrome better support
- Safari support
- dev branching our of master