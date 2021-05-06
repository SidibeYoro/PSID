FROM maven:3.6.3-jdk-11-slim AS build
RUN mkdir -p /workspace
WORKDIR /workspace
COPY pom.xml /workspace
COPY src /workspace/src
RUN mvn -B -f pom.xml clean package -DskipTests


FROM openjdk:11-jdk-slim
COPY --from=build /workspace/target/givemehand.jar givemehand.jar
EXPOSE 8080
#ARG JAR_FILE=*.jar
#COPY target/${JAR_FILE} givemehand.jar
#COPY . .
ENTRYPOINT ["java","-jar", "givemehand.jar"]

