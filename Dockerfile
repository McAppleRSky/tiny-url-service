FROM openjdk:11-jdk as builder
workdir application
ARG JAR_FILE=service/build/libs/service-0.0.1-SNAPSHOT.jar
COPY ${JAR_FILE} application.jar
RUN java -Djarmode=layertools -jar application.jar extract

FROM openjdk:11-jre
WORKDIR application
COPY --from=builder application/spring-boot-loader/ ./
COPY --from=builder application/dependencies/ ./
COPY --from=builder application/snapshot-dependencies/ ./
COPY --from=builder application/application/ ./
ENTRYPOINT ["java", "org.springframework.boot.loader.JarLauncher"]

#ENTRYPOINT ["java", "-Djava.security.egd=file:/dev/.urandom", "-XX:+UseContainerSupport", "-Xmx512m", "-Xms128m", "-jar"]
