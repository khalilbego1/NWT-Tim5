#!/bin/sh
while ! nc -z eureka-server 8761 ; do
    echo "Waiting for the Eureka Server"
    sleep 3
done
while ! nc -z user-management 8000 ; do
    echo "Waiting for the User Management service"
    sleep 3
done
while ! nc -z location-transport 8050 ; do
    echo "Waiting for the Location Transport service"
    sleep 3
done
while ! nc -z arrangements 9000 ; do
    echo "Waiting for the Arrangements service"
    sleep 3
done

java -jar /opt/api-gateway-0.0.1-SNAPSHOT.jar