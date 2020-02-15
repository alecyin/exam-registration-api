FROM openjdk:8
#把可执行jar包复制到基础镜像的根目录下
VOLUME /tmp
COPY target/exam-registration-backend-0.0.1.jar exam-registration-backend.jar
ADD exam-registration-backend.jar /exam-registration-backend.jar
#容器需要暴露 给主机的端口号,这里的端口号和 项目配置文件中的端口号一致
EXPOSE 8082
#在镜像运行为容器后执行的命令
ENTRYPOINT ["java","-jar","/exam-registration-backend.jar"]
