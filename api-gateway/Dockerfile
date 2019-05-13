FROM openjdk:8-jdk-alpine
ENV APP_HOME /opt
RUN apk --no-cache add netcat-openbsd
COPY target/api-gateway-0.0.1-SNAPSHOT.jar $APP_HOME
COPY ApiGateway-entrypoint.sh $APP_HOME/ApiGateway-entrypoint.sh
RUN chmod 775 $APP_HOME/ApiGateway-entrypoint.sh
WORKDIR $APP_HOME
ENTRYPOINT ["sh", "-c"]

CMD [ "/opt/ApiGateway-entrypoint.sh" ]
EXPOSE 8000