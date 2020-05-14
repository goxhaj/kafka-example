# Simple Kafka App

## Start Kafka
Download kafka from: https://kafka.apache.org/downloads
and unzip into a folder named "kafka" then perfom next steps.
### Start Zookeeper
➜  kafka bin/zookeeper-server-start.sh config/zookeeper.properties
### Start Kafka Server
➜  kafka bin/kafka-server-start.sh config/server.properties
### Start a Producer with a topic "test"
➜  kafka bin/kafka-console-producer.sh --bootstrap-server localhost:9092 --topic test
### Start a consumer with a topic "test"
➜  kafka bin/kafka-console-consumer.sh --bootstrap-server localhost:9092 --topic test --from-beginning

## Start the app
mvn spring-boot:run

## Swagger
Go to: localhost:8080/swagger-ui.html and make a post

