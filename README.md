This is a guide on how to run **raft** = RyanAir Automated Functional Test. 


WHY
---
Playing Ryanair site. They are functional and run locally. 


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
- Allure


RUN
---
From command line:

```
mvn clean test -Dbrowser=ff
mvn clean test -Dbrowser=chrome
```

Sit, watch, touch nothing and enjoy! 


REPORT
------

See report locally: 

```
mvn site
```

To see report open local file `/site/allure-maven-plugin.html` (or run 
`mvn jetty:run` and open `http://localhost:8080` in your browser). 

DOC
---

Just Java docs [here](doc/index.html).


TODO
----

This project is *somewhat* not finished. 

Define *somewhat*: 

- Cucumber tests

then: 

- already booked error support (fix)

and perhaps:

- Safari support
- dev branching our of master