FROM openjdk:8-jdk-alpine
ENV APP_HOME /opt
RUN apk --no-cache add netcat-openbsd
COPY target/user-management-0.0.1-SNAPSHOT.jar $APP_HOME
COPY UserManagement-entrypoint.sh $APP_HOME/UserManagement-entrypoint.sh
RUN chmod 775 $APP_HOME/UserManagement-entrypoint.sh
WORKDIR $APP_HOME
ENTRYPOINT ["sh", "-c"]

CMD [ "/opt/UserManagement-entrypoint.sh" ]
EXPOSE 8000