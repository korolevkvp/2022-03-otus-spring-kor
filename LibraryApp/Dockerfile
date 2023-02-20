FROM maven:3.8-openjdk-11
ENV PROJECT_DIR=/opt/project
RUN mkdir -p $PROJECT_DIR
WORKDIR $PROJECT_DIR
ADD ./pom.xml $PROJECT_DIR
RUN mvn dependency:resolve
ADD ./src/ $PROJECT_DIR/src
RUN mvn install -DskipTests -Dcheckstyle.skip -Dasciidoctor.skip

FROM openjdk:11
ENV PROJECT_DIR=/opt/project
RUN mkdir -p $PROJECT_DIR
WORKDIR $PROJECT_DIR
COPY --from=0 $PROJECT_DIR/target/2202-03-otus-spring-kor* $PROJECT_DIR
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "/opt/project/2202-03-otus-spring-kor.jar"]
