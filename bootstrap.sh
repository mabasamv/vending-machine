#!/bin/sh

echo "Starting bootstrap"
container_id=$(hostname)
echo "Container id: ${container_id}"

/usr/local/openjdk-8/bin/java -jar /vending-machine/app.jar
