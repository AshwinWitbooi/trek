FROM openjdk:8-jdk-alpine
COPY /target/*.jar app.jar
ENTRYPOINT ["java","-Djasypt.encryptor.password=trek","-Dspring.profiles.active=prod","-jar","/app.jar"]