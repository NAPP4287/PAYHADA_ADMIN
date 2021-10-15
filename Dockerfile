FROM openjdk:8-jdk-alpine
VOLUME /tmp
EXPOSE 8005
ARG JAR_FILE=build/libs/payhadaadmin-0.0.1-SNAPSHOT.jar
ADD ${JAR_FILE} clickping-api.jar
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/clickping-api.jar","-Dspring.profiles.active=test"]
