This is a guide on how to run **raft** = RyanAir Automated Functional Test. 


WHY
---
Playing Ryanair site. Tests are functional and run locally. 


GET
---
Get from Github: 
```
git clone https://github.com/sikorka/raft.git
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
- FF 46.0.1, Chrome 51.0 
  (Safari 10.0 won't work, FF > 46.0.1 neither)
- Java 1.8
- Maven 3.3.9
- JUnit
- Selenium
- Spock
- Hamcrest
- Allure

It is important to have the specific versions installed, otherwise things might not fly.

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

Generate report locally: 

```
mvn site
```

To see report: 

```
open ./target/site/allure-maven-plugin.html
```

or run `mvn jetty:run` and open `open http://localhost:8080` 


DOC
---

Just Java docs `open ./doc/index.html`.


TODO
----

This project is *somewhat* not finished. 

Define *somewhat*: 

- Cucumber tests

then: 

- Safari support
- dev branching our of master


TROUBLES ?
----------

### chrome does not launch

 - download chromedriver and install
 - remove ~/.m2/repository/webdriver/chromedriver (or try running `mvn clean test -Dwebdriver.chrome.driver=/usr/local/bin/chromedriver`)
 
### allure not found

put in your ~/.m2/settings.xml allure

```
<settings>
<pluginGroups>
  <!-- your existing plugin groups if any -->

  <pluginGroup>ru.yandex.qatools.allure</pluginGroup>
</pluginGroups>
</settings>
```