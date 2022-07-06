FROM openjdk:11
COPY /target/2202-03-otus-spring-kor.jar /app/app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "/app/app.jar"]
