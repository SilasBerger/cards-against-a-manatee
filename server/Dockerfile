FROM openjdk:11.0.14.1-jre-buster

COPY target/cards-against-a-manatee-0.0.1-SNAPSHOT.jar manatee.jar

COPY cards /etc/cards
ENV CARD_PACKS_BASE_PATH=/etc/cards

ENTRYPOINT ["java", "-jar", "/manatee.jar"]
