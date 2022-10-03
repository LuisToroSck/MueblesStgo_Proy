FROM openjdk:17
ARG JAR_FILE=target/MueblesStgo.jar
COPY ${JAR_FILE} MueblesStgo.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","/MueblesStgo.jar"]