# Étape 1 : Build de l'application avec Maven
FROM maven:3.8.5-openjdk-17 AS build
COPY . .
RUN mvn clean package -DskipTests

# Étape 2 : Exécution de l'application Java
FROM openjdk:17-jdk-slim
# CORRECTION ICI : Le jar est buildé dans le dossier target local du dossier store
COPY --from=build target/*.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","app.jar"]
