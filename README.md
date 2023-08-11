# Vending Machine
This project simulates a vending machine, it should be able to perform the following

1.	Start up with preconfigured items as well as petty cash.
2.	Allow a user to add items to be purchased.
3.	Facilitate payment and store petty cash by means of known currency denominations.
4.	Issue change by known currency denominations.
5.	Adjust available quantity of items and petty cash.

## Getting started
- Run `mvn clean install` to install the maven dependencies.

## Running tests
Run `mvn test` to execute the unit tests or alternatively from the maven projects tab.

## Running SonarQube on Docker
Run `docker run -d --name sonarqube -p 9000:9000 -p 9092:9092 sonarqube:7.8-community` to download and run sonarqube in Docker. Sonarqube will be available on http://localhost:9000
- **Username**: admin
- **Password**: admin

Run `mvn sonar:sonar`

## Running the application
The application will be available on http://localhost:8080

### Using the IDE
Click the `Run` or `Debug` icon or alternatively select it from the dropdown menu under Run.

### Using the terminal
- Open up a terminal window and navigate to the project folder
- Run `java -jar <location of jar file>` the jar file should be located in the target folder.

### Using maven
- Open up a terminal window and navigate to the project folder.
- Run `mvn spring-boot:run`

### Using docker
- Install Docker
- Start your Docker daemon
- Open a terminal window
- In the terminal window navigate to the project folder
- Run `docker build -t vending-machine-image .` to create a docker image
- Run `docker images` to check docker images
- Run `docker run -it -p 8080:8080 --name vending-machine-app vending-machine-image`

### Swagger URL
http://localhost:8080/vending-machine/swagger-ui/index.html

## Accessing the database
The database will be available on http://localhost:8080/vending-machine/h2-console
- **Driver Class**: org.h2.Driver
- **JDBC URL**: jdbc:h2:mem:vending-machine
- **Username**: sa
- **Password**: password