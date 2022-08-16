#本镜像的基础镜像。有兴趣的话，可以自行在DockerHub上搜索openjdk，分析官方的openjdk镜像的Dockerfile文件。这里为什么不用oracle提供的jdk（jre）？简单地讲，版权问题。
FROM openjdk:17.0.2-jdk-slim
#COPY . /usr/src/myapp
#WORKDIR /usr/src/myapp
ADD ./target/car-rental-4-0.0.1.jar car-rental.jar
#RUN javac Main.java
#CMD ["java", "Main"]
ENTRYPOINT java -jar jar car-rental.jar