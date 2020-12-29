#FROM openjdk:8
#VOLUME /tmp
#MAINTAINER 1562676731@qq.com
#ADD ./target/expand-0.0.1-SNAPSHOT.jar expand.jar
#RUN sh -c 'touch /expand.jar'
#ENTRYPOINT ["sh", "-c", "java $JAVA_OPTS -Djava.security.egd=file:/dev/./urandom -jar /expand.jar"]
FROM openjdk:8-jdk-alpine
MAINTAINER 1562676731@qq.com
VOLUME /tmp/log
ENV TZ=Asia/Shanghai
RUN ln -snf /usr/share/zoneinfo/$TZ /etc/localtime && echo $TZ > /etc/timezone && mkdir -p /expand
WORKDIR /expand
EXPOSE 4000
ADD ./target/expand-0.0.1-SNAPSHOT.jar expand.jar
ENTRYPOINT ["java","-Xms1024m","-Xmx1024m","-Dspring.profiles.active=test","-Djava.security.egd=file:/dev/./urandom","-jar","/expand.jar"]
