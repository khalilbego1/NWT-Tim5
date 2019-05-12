FROM openjdk:8-jdk-alpine
ENV APP_HOME /opt

ARG dburl
ARG dbuser
ARG dbpass

RUN apk --no-cache add netcat-openbsd
COPY target/user-management-0.0.1-SNAPSHOT.jar $APP_HOME
COPY UserManagement-entrypoint.sh $APP_HOME/UserManagement-entrypoint.sh

RUN echo "spring.datasource.url=$dburl">$APP_HOME/application.properties
RUN echo -n "spring.datasource.username=$dbuser">>$APP_HOME/application.properties
RUN echo -n "spring.datasource.password=$dbpass">>$APP_HOME/application.properties

RUN chmod 775 $APP_HOME/UserManagement-entrypoint.sh
WORKDIR $APP_HOME
ENTRYPOINT ["sh", "-c"]

CMD [ "/opt/UserManagement-entrypoint.sh" ]
EXPOSE 8000