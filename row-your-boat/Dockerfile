FROM maven:3.9.8-eclipse-temurin-21 AS build

WORKDIR /home/maven/src

COPY pom.xml .
COPY src ./src

RUN mvn clean package -DskipTests

FROM eclipse-temurin:21-jre

EXPOSE 8082

# Copy the JAR file built in the previous stage to the container's /app directory
COPY --from=build /home/maven/src/target/row-your-boat-0.0.1-SNAPSHOT.jar /app/row-your-boat.jar

# Run the Java application
CMD ["java", "-jar", "/app/row-your-boat.jar"]
