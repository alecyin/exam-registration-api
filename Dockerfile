FROM openjdk:8
VOLUME /tmp
COPY target/exam-registration-api-0.0.1.jar exam-registration-api.jar
COPY  src/main/resources/templates/photo /data/photo/
EXPOSE 8082
ENTRYPOINT ["java","-jar","/exam-registration-api.jar"]
