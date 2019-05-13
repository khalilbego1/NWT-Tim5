FROM openjdk:8-jdk-alpine
ENV APP_HOME /opt
EXPOSE 8761
COPY target/eureka-server-0.0.1-SNAPSHOT.jar $APP_HOME
ENTRYPOINT ["java", "-jar", "/opt/eureka-server-0.0.1-SNAPSHOT.jar"]