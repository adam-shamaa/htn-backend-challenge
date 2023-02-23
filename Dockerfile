FROM maven:3.9.0-amazoncorretto-17
WORKDIR /app
COPY . /app
CMD ["mvn", "spring-boot:run"]