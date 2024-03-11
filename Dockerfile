FROM openjdk:17
MAINTAINER iyke-israel
COPY target/*.jar app.jar
ENTRYPOINT ["java", "-jar", "/quickstart-0.0.1-SNAPSHOT.jar"]