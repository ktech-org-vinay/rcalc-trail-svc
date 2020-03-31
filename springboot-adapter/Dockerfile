FROM 067805054192.dkr.ecr.eu-west-1.amazonaws.com/ktech/java:11-jre-slim

ARG SYSTEM_CODE=rcalc-trail-svc
# Environment variables
ENV SYSTEM_CODE=${SYSTEM_CODE}
ENV SSM_NAMESPACE=ecs-container-env
ENV DD_SERVICE_NAME=${SYSTEM_CODE}

COPY build/libs/springboot-adapter-0.1.0.jar app.jar
ENV APP_PROFILE=default
EXPOSE 8080

CMD ["java", "-Xlog:gc*:time", \
     "-Xms256m", "-Xmx4g", "-XX:NewSize=256m", "-XX:MaxNewSize=256m", \
     "-jar", "-Dspring.profiles.active=${APP_PROFILE}", "app.jar"]
