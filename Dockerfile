FROM adoptopenjdk/openjdk11:alpine-jre

WORKDIR coding-challenge

COPY build/libs/*.jar app.jar

ENTRYPOINT ["java","-jar","app.jar"]