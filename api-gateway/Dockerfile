FROM openjdk:8-jdk-alpine
ENV APP_HOME /opt
EXPOSE 8585
COPY target/api-gateway-0.0.1-SNAPSHOT.jar $APP_HOME
ARG JAR_FILE=target/api-gateway-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java", "-jar", "/opt/api-gateway-0.0.1-SNAPSHOT.jar"]
