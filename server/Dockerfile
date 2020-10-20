FROM openjdk:11-jre-slim

WORKDIR /app
COPY ./target/cards-against-a-manatee-0.1.0.jar /app

EXPOSE 8080

CMD ["java", "-jar", "cards-against-a-manatee-0.1.0.jar"]