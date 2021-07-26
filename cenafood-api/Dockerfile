FROM openjdk:11-jre

WORKDIR /app

ARG JAR_FILE

ADD target/${JAR_FILE} /app/api.jar
ADD wait-for-it.sh /wait-for-it.sh 

RUN chmod +x /wait-for-it.sh
	
EXPOSE 8080

CMD ["java", "-jar", "api.jar"]