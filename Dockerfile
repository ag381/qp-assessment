FROM openjdk:21-jdk

COPY /target/*.jar grocery.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "grocery.jar"]