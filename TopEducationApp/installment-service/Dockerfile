FROM openjdk:17
ARG JAR_FILE=target/tem-installment-service.jar
COPY ${JAR_FILE} tem-installment-service.jar
ENTRYPOINT ["java","-jar","/tem-installment-service.jar"]