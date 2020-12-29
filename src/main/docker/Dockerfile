FROM openjdk:8-jdk-alpine
VOLUME /tmp
ADD ./target/expand-0.0.1-SNAPSHOT.jar expand.jar
RUN sh -c 'touch /expand.jar'
ENTRYPOINT ["sh", "-c", "java $JAVA_OPTS -Djava.security.egd=file:/dev/./urandom -jar /expand.jar"]