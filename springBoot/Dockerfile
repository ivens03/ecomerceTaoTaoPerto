FROM eclipse-temurin:21-jre-alpine
RUN mkdir /app
WORKDIR /app
COPY target/*.jar /app/app.jar
CMD ["sh", "-c", "java $JAVA_OPTS -jar /app/app.jar"]