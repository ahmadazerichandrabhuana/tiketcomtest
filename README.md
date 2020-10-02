# Tiket.com Test

This is repository to complete Tiket.com's assignment.

## Prerequisites

* [Java SDK 8](http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html)
* [Git](https://git-scm.com/downloads)
* [Intellij IDEA](https://www.jetbrains.com/idea/download/#section=windows)
* [Maven](https://maven.apache.org/download.cgi)
* [Allure](https://docs.qameta.io/allure/#_installing_a_commandline)

## Run Tests

    mvn test -Dconfig=config.cfg -DsuiteXmlFile=xml\Transaction.xml

## Opening Allure Report (after finish Run Tests)

    allure serve

## Notes

* Chrome used on this code : Chrome85.