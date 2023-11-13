FROM openjdk:17
ARG JAR_FILE=target/tem-adminoffice-service.jar
COPY ${JAR_FILE} tem-adminoffice-service.jar
ENTRYPOINT ["java","-jar","/tem-adminoffice-service.jar"]