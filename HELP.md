# Getting Started

### Reference Documentation
For further reference, please consider the following sections:

* [Official Apache Maven documentation](https://maven.apache.org/guides/index.html)
* [Spring Boot Maven Plugin Reference Guide](https://docs.spring.io/spring-boot/docs/2.5.6/maven-plugin/reference/html/)
* [Create an OCI image](https://docs.spring.io/spring-boot/docs/2.5.6/maven-plugin/reference/html/#build-image)
* [Spring Web](https://docs.spring.io/spring-boot/docs/2.5.6/reference/htmlsingle/#boot-features-developing-web-applications)
* [Spring Data JPA](https://docs.spring.io/spring-boot/docs/2.5.6/reference/htmlsingle/#boot-features-jpa-and-spring-data)

### Guides
The following guides illustrate how to use some features concretely:

* [Building a RESTful Web Service](https://spring.io/guides/gs/rest-service/)
* [Serving Web Content with Spring MVC](https://spring.io/guides/gs/serving-web-content/)
* [Building REST services with Spring](https://spring.io/guides/tutorials/bookmarks/)
* [Accessing Data with JPA](https://spring.io/guides/gs/accessing-data-jpa/)


### Install Developer Guide

#### Prerequisite

- git (you can download it in https://git-scm.com/downloads)
- jdk 8 ( you can download it in https://docs.aws.amazon.com/corretto/latest/corretto-8-ug/downloads-list.html)
- Defined JAVA_HOME enrivonment variable in your system

#### Steps

  Clone the git repository
  
  ``` git clone git@github.com:GBank-1/gbank.git```
  
  Go into project folder
  
  ``` cd gbank ```
  
  Execute maven:
  
  build:
  
  ``` .\mvnw clean install ```
  
  deploy in embedded tomcat :
  
  ``` .\mvnw spring-boot:run ```
  
  Note: You could need before run it, in linux System, get execution permission for mvnw file
  
  
  
  Api Doc:
    [http://localhost:8080/swagger-ui/index.html](http://localhost:8080/swagger-ui/index.html)




