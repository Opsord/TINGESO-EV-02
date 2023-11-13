FROM openjdk:17
ARG JAR_FILE=target/tem-student-service.jar
COPY ${JAR_FILE} tem-student-service.jar
ENTRYPOINT ["java","-jar","/tem-student-service.jar"]