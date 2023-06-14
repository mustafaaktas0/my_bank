FROM openjdk:17-jdk-slim AS build

COPY pom.xml mvnw ./
COPY .mvn .mvn
RUN ./mvnw dependency:resolve

COPY src src
RUN ./mvnw package

FROM openjdk:17-jdk-slim
WORKDIR my_bank
COPY --from=build target/*.jar weather.jar
ENTRYPOINT ["java", "-jar", "my_bank.jar"]