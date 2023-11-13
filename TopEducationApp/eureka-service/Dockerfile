FROM openjdk:17
ARG JAR_FILE=target/tem-eureka-service.jar
COPY ${JAR_FILE} tem-eureka-service.jar
ENTRYPOINT ["java","-jar","/tem-eureka-service.jar"]