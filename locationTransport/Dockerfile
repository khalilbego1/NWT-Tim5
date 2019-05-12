FROM openjdk:8-jdk-alpine
ENV APP_HOME /opt

ARG dburl
ARG dbuser
ARG dbpass
ARG rabbithost

RUN apk --no-cache add netcat-openbsd
COPY target/locationTransport-0.0.1-SNAPSHOT.jar $APP_HOME
COPY LocationTransport-entrypoint.sh $APP_HOME/LocationTransport-entrypoint.sh

RUN echo "spring.datasource.url=$dburl">$APP_HOME/application.properties
RUN echo "spring.datasource.username=$dbuser">>$APP_HOME/application.properties
RUN echo "spring.datasource.password=$dbpass">>$APP_HOME/application.properties
RUN echo "spring.rabbitmq.host=$rabbithost">>$APP_HOME/application.properties

RUN chmod 775 $APP_HOME/LocationTransport-entrypoint.sh
WORKDIR $APP_HOME
ENTRYPOINT ["sh", "-c"]

CMD [ "/opt/LocationTransport-entrypoint.sh" ]
EXPOSE 8000