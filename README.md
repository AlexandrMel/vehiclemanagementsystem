# vehiclemanagementsystem
vehiclec management system



# Getting Started

## Tech stack & Open-source libraries

### Server - Backend

* 	[JDK](http://www.oracle.com/technetwork/java) - Java™ Platform, Standard Edition Development Kit
* 	[Spring Boot](https://spring.io/projects/spring-boot) - Framework to ease the bootstrapping and development of new Spring Applications
* 	[Maven](https://maven.apache.org/) - Dependency Management

## Running the application locally

### Running the application with IDE

There are several ways to run a Spring Boot application on your local machine. One way is to execute the `main` method in the `com.vehicle.management.VehicleManagementApplication` class from your IDE.

* 	Download the zip or clone the Git repository.
* 	Unzip the zip file (if you downloaded one)
* 	Open Command Prompt and Change directory (cd) to folder containing pom.xml
* 	Open Eclipse
	* File -> Import -> Existing Maven Project -> Navigate to the folder where you unzipped the zip
	* Select the project
* 	Choose the Spring Boot Application file (search for @SpringBootApplication)
* 	Right Click on the file and Run as Java Application

### Running the application with Maven

Alternatively you can use the [Spring Boot Maven plugin](https://docs.spring.io/spring-boot/docs/current/reference/html/build-tool-plugins-maven-plugin.html) like so:

```shell
$ git clone https://github.com/anilmehrotra/vehiclemanagementsystem.git
$ cd vehiclemanagementsystem
$ mvn spring-boot:run
```

### Running the application with Executable JAR

The code can also be built into a jar and then executed/run. Once the jar is built, run the jar by double clicking on it or by using the command 

```shell
$ git clone https://github.com/anilmehrotra/vehiclemanagementsystem.git
$ cd vehiclemanagementsystem
$ mvn clean install
$ java -jar target/vehiclemanagement.jar
```

To shutdown the jar, follow the below mentioned steps on a Windows machine.

*	In command prompt execute the **jcmd** command to print a list of all running Java processes
*	**Taskkill /PID PROCESS_ID_OF_RUNNING_APP /F** execute this command by replacing the **PROCESS_ID_OF_RUNNING_APP** with the actual process id of the running jar found out from executing the previous command

### Running the application with Docker 

```shell
$ git clone https://github.com/anilmehrotra/vehiclemanagementsystem.git
$ cd vehiclemanagementsystem
$ mvn clean install
To build docker image execute below command
$ docker build -t vehicle/vehiclemanagementsystem .
To list docker images and validate image created successfully
$ docker images
To create docker container execute below command 
$ docker run -p 8080:8080 --name=vehiclemanagementsystem -d vehicle/vehiclemanagementsystem
To list all running container and validate container for vehiclemanagementsystem created successfully 
$ docker ps -a
Once docker container created successfully to See logs execute below command
$ docker logs vehiclemanagementsystem
To go inside container
$ docker exec -it vehiclemanagementsystem /bin/bash
```

### Swagger document for Rest API

Once Application running use below URL from browser

```
[For raw json schema] - http://localhost:8080/vehiclemanagementsystem/v3/api-docs

[For Swagger UI] - http://localhost:8080/vehiclemanagementsystem/swagger-ui/index.html?configUrl=/vehiclemanagementsystem/v3/api-docs/swagger-config

** Use ip of the host machine and port no used for deployment 
or use domain name mapped
```

