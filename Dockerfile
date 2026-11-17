FROM gradle:8.4.0-jdk21-alpine

WORKDIR /app
COPY . /app


RUN gradle :digital-service:bootJar

# RUN chmod +x /app/scripts/start.sh && \
#     gradle :digital-service:bootJar

EXPOSE 8080
EXPOSE 8081

# ENTRYPOINT "/app/scripts/start.sh"
# ENTRYPOINT [""]
ENTRYPOINT ["java", "-jar", "digital-service/build/libs/internship-service.jar"]