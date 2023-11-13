FROM openjdk:17
ARG JAR_FILE=target/tem-gateway-service.jar
COPY ${JAR_FILE} tem-gateway-service.jar
ENTRYPOINT ["java","-jar","/tem-gateway-service.jar"]