FROM openjdk:8-jdk-alpine

MAINTAINER Laijin Lu <1@aikin.me>

COPY build/libs/bicyclestore-order-service.jar /app/bicyclestore-order-service.jar

WORKDIR /app

CMD ["java", "-jar", "-Xmx2048m", "bicyclestore-order-service.jar"]
