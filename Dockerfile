FROM amazoncorretto:17
WORKDIR /app
COPY ./target/gallery-1.0.0.jar .
EXPOSE 8080
ENTRYPOINT java -jar gallery-1.0.0.jar