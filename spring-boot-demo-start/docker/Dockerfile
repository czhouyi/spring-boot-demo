#FROM frolvlad/alpine-oraclejdk8:slim
FROM java:8
VOLUME /tmp
ADD file-reader-1.4.2.jar app.jar
RUN bash -c 'touch /app.jar'
EXPOSE 8080
ENV JAVA_OPTS=""
ENTRYPOINT [ "sh", "-c", "java $JAVA_OPTS -Djava.security.egd=file:/dev/./urandom -jar /app.jar" ]
MAINTAINER czhouyi