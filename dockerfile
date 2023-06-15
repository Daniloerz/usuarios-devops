FROM openjdk:11
COPY /build/libs/usuario-powerUp-0.0.1-SNAPSHOT.jar usuario.jar
EXPOSE 8081 3306
ENTRYPOINT ["java","-jar","/usuario.jar"]