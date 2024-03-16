FROM openjdk:17
COPY cirkuits-api-0.0.1-SNAPSHOT.jar cirkuits-api-dev.jar
COPY verifyEmail.html /src/main/resources/templates/verifyEmail.html
COPY logo_green.png /src/main/resources/templates/logo_green.png
ENTRYPOINT ["java", "-jar", "cirkuits-api-dev.jar"]