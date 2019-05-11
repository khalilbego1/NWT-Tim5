FROM openjdk:8-jdk-alpine
ENV APP_HOME /opt
RUN apk --no-cache add netcat-openbsd
COPY target/locationTransport-0.0.1-SNAPSHOT.jar $APP_HOME
COPY LocationTransport-entrypoint.sh $APP_HOME/LocationTransport-entrypoint.sh
RUN chmod 775 $APP_HOME/LocationTransport-entrypoint.sh
WORKDIR $APP_HOME
ENTRYPOINT ["sh", "-c"]

CMD [ "/opt/LocationTransport-entrypoint.sh" ]
EXPOSE 8000