FROM openjdk:8-jdk-alpine
ARG eurekahost
ENV APP_HOME /opt
EXPOSE 8761
COPY target/eureka-server-0.0.1-SNAPSHOT.jar $APP_HOME
RUN echo "eureka.instance.hostname=$eurekahost">>$APP_HOME/application.properties
ENTRYPOINT ["java", "-jar", "/opt/eureka-server-0.0.1-SNAPSHOT.jar"]