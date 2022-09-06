FROM adoptopenjdk/maven-openjdk11
VOLUME /tmp
COPY target/*.jar app.jar
COPY ./ ./
EXPOSE 8888
ENTRYPOINT ["java","-jar","/app.jar"]