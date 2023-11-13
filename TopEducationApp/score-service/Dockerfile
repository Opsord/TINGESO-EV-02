FROM openjdk:17
ARG JAR_FILE=target/tem-score-service.jar
COPY ${JAR_FILE} tem-score-service.jar
ENTRYPOINT ["java","-jar","/tem-score-service.jar"]