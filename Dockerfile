FROM openjdk:17-alpine
COPY ./target/cab_data_researcher-0.0.1-SNAPSHOT.jar /trip-api.jar
EXPOSE 8081
ENTRYPOINT ["java","-jar","-Dspring.profiles.active=cloud","trip-api.jar"]