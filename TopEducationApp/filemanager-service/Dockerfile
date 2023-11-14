FROM openjdk:17
ARG JAR_FILE=target/tem-filemanager-service.jar
COPY ${JAR_FILE} tem-filemanager-service.jar
ENTRYPOINT ["java","-jar","/tem-filemanager-service.jar"]