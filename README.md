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
Tested on: 

 - Mac OSX El Capitan and 
 - Microsoft Windows 8.1 
    (tested without report generation though) 

So if you have one that's great! It might work on other platforms but was not tested. 


Anyhow here is what you need: 

 - setup browsers locally: 
    FF 46.0.1 and/or Chrome 51.0 
    (Safari 10.0 won't work, FF > 46.0.1 neither) 
 - setup JDK 1.8 locally 
 - setup Maven 3.3.9 locally 

It is important to have the specific versions installed, otherwise things might not fly. 


TECH
----
Used technologies / tools: 
- JUnit
- Selenium
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

First download chromedriver, install. Then: 

- put it in your `PATH` variable or
- define driver in `src\test\resources\system.properties` or
- from command line:
```
mvn clean test -Dbrowser=chrome -Dwebdriver.chrome.driver=/usr/local/bin/chromedriver
mvn clean test -Dbrowser=chrome -Dwebdriver.chrome.driver=C\Users\You\Programs\chromedrier.exe
```

And you might need to: 

- remove `~/.m2/repository/webdriver/chromedriver` 


### allure not found 

Put allure in your `~/.m2/settings.xml`: 

```
<settings>
<pluginGroups>
  <!-- your existing plugin groups if any -->

  <pluginGroup>ru.yandex.qatools.allure</pluginGroup>
</pluginGroups>
</settings>
```
