FROM maven:3.8.3-openjdk-17 AS MAVEN_BUILD

# Set up working directory
WORKDIR /app

# Copy pom.xml and source code to working directory
COPY pom.xml .
COPY src ./src

# Build the application with Maven
RUN mvn clean package

FROM openjdk:17-alpine

# Set up working directory add coy build jar file
WORKDIR /app
COPY --from=MAVEN_BUILD /app/target/Device-Management-System.jar .

ENV TZ=Asia/Kolkata

RUN apk add --no-cache fontconfig ttf-dejavu
EXPOSE 8093

# Set the default command to run the application
CMD ["java", "-jar", "Device-Management-System.jar"]