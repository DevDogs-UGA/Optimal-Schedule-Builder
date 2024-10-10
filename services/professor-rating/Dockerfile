FROM docker.io/maven:3-eclipse-temurin-21-alpine as base
WORKDIR /app

# deps
COPY pom.xml ./
RUN mvn dependency:go-offline
# work-around for go-offline not downloading all deps (MDEP-82)
RUN mvn verify --fail-never -Dmaven.test.skip

# build
COPY src/ src/
RUN mvn package -Dmaven.test.skip

RUN mv target/*.jar app.jar

CMD ["java", "-jar", "app.jar"]
