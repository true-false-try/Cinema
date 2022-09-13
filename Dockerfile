FROM adoptopenjdk/maven-openjdk11
VOLUME /tmp
WORKDIR .
COPY ./ ./
RUN mvn clean package -DskipTests
COPY target/*.jar app.jar
EXPOSE 8888
ENTRYPOINT ["java","-jar","/app.jar"]