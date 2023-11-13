FROM openjdk:17
ARG JAR_FILE=target/tdm-config-service.jar
COPY ${JAR_FILE} tdm-config-service.jar
ENTRYPOINT ["java","-jar","/tdm-config-service.jar"]