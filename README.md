## Running the application
You can run your application using:

`./mvnw spring-boot:run`

## Spring Profiles

If you want to tun the application with a specific profile, add the following JVM system parameter:

`-Dspring-boot.run.profiles=dev`

## Packaging and running the application
The application can be packaged using:

`./mvnw package`


## MongoDB Docker

```shell script
docker network create mongodb-network

docker run -d --network mongodb-network --restart unless-stopped --name mongodb-dev \
 -e MONGO_INITDB_ROOT_USERNAME=mongoadmin \
 -e MONGO_INITDB_ROOT_PASSWORD=secret \
 -p 27017:27017 mongo:7.0.7

docker run -d --network mongodb-network --restart unless-stopped --name mongo-ui \
 -e ME_CONFIG_MONGODB_URL=mongodb://mongoadmin:secret@mongodb-dev:27017/ \
 -p 5555:8081 mongo-express:1.0.2-20
```
