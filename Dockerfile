FROM openjdk:17
ADD target/restaurant-app.jar app.jar
ENTRYPOINT ["java", "-jar", "app.jar"]