FROM openjdk:8-jre
RUN apt-get update -y
RUN mkdir /vending-machine
WORKDIR /vending-machine
COPY target/*.jar /vending-machine/app.jar
COPY bootstrap.sh ./
EXPOSE 8080
CMD ["/bin/bash", "/vending-machine/bootstrap.sh"]