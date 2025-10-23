FROM openjdk:21-slim
RUN mkdir /app
WORKDIR /app
COPY target/*.jar /app/app.jar
CMD ["sh", "-c", "java $JAVA_OPTS -jar /app/app.jar"]