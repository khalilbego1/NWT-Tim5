FROM openjdk:8-jdk-alpine
ENV APP_HOME /opt
RUN apk --no-cache add netcat-openbsd
COPY target/arrangements-0.0.1-SNAPSHOT.jar $APP_HOME
COPY Arrangements-entrypoint.sh $APP_HOME/bin/Arrangements-entrypoint.sh
RUN chmod 755 $APP_HOME/bin/Arrangements-entrypoint.sh
EXPOSE 9000