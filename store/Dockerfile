# Étape 1 : Build de l'application avec Maven et Java 22
FROM maven:3.9.6-eclipse-temurin-22 AS build
COPY . .
# AJOUT ICI : On ignore la compilation ET l'exécution des tests
RUN mvn clean package -Dmaven.test.skip=true

# Étape 2 : Exécution de l'application Java avec le JRE 22
FROM eclipse-temurin:22-jre-alpine
COPY --from=build target/*.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","app.jar"]
