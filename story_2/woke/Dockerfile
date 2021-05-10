FROM openjdk:11-jdk

ENV JAR_NAME woke-*.jar

COPY build/libs/$JAR_NAME $JAR_NAME

EXPOSE 8080

ENTRYPOINT java -Duser.timezone=America/Sao_Paulo -jar $JAR_NAME
