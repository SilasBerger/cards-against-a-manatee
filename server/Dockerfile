# FROM tomcat:8.5.78-jre8-temurin-focal
# COPY web.xml /usr/local/tomcat/conf
# COPY server.xml /usr/local/tomcat/conf
# COPY target/cards-against-a-manatee-0.0.1-SNAPSHOT.war /usr/local/tomcat/webapps/ManateeServer.war

FROM openjdk:11.0.14.1-jre-buster
COPY target/cards-against-a-manatee-0.0.1-SNAPSHOT.jar manatee.jar
ENTRYPOINT ["java", "-jar", "/manatee.jar"]