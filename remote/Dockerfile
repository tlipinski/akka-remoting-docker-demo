FROM java:7
ADD target/scala-2.11/remote-assembly-0.1-SNAPSHOT.jar app.jar
EXPOSE 2552
CMD ["java", "-jar", "app.jar"]
