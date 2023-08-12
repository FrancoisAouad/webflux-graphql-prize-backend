# Getting Started

### Reference Documentation
For further reference, please consider the following sections:

* [Official Apache Maven documentation](https://maven.apache.org/guides/index.html)
* [Spring Boot Maven Plugin Reference Guide](https://docs.spring.io/spring-boot/docs/3.1.0/maven-plugin/reference/html/)
* [Create an OCI image](https://docs.spring.io/spring-boot/docs/3.1.0/maven-plugin/reference/html/#build-image)
* [GraalVM Native Image Support](https://docs.spring.io/spring-boot/docs/3.1.0/reference/html/native-image.html#native-image)
* [Spring Boot Testcontainers support](https://docs.spring.io/spring-boot/docs/3.1.0/reference/html/features.html#features.testing.testcontainers)
* [Testcontainers MongoDB Module Reference Guide](https://www.testcontainers.org/modules/databases/mongodb/)
* [Docker Compose Support](https://docs.spring.io/spring-boot/docs/3.1.0/reference/htmlsingle/#features.docker-compose)
* [Spring Reactive Web](https://docs.spring.io/spring-boot/docs/3.1.0/reference/htmlsingle/#web.reactive)
* [Spring for GraphQL](https://docs.spring.io/spring-boot/docs/3.1.0/reference/html/web.html#web.graphql)
* [Spring Data Reactive Redis](https://docs.spring.io/spring-boot/docs/3.1.0/reference/htmlsingle/#data.nosql.redis)
* [Spring Data Reactive MongoDB](https://docs.spring.io/spring-boot/docs/3.1.0/reference/htmlsingle/#data.nosql.mongodb)
* [WebSocket](https://docs.spring.io/spring-boot/docs/3.1.0/reference/htmlsingle/#messaging.websockets)
* [Validation](https://docs.spring.io/spring-boot/docs/3.1.0/reference/htmlsingle/#io.validation)
* [Java Mail Sender](https://docs.spring.io/spring-boot/docs/3.1.0/reference/htmlsingle/#io.email)
* [Spring Boot Actuator](https://docs.spring.io/spring-boot/docs/3.1.0/reference/htmlsingle/#actuator)
* [Prometheus](https://docs.spring.io/spring-boot/docs/3.1.0/reference/htmlsingle/#actuator.metrics.export.prometheus)
* [Testcontainers](https://www.testcontainers.org/)

### Guides
The following guides illustrate how to use some features concretely:

* [Building a Reactive RESTful Web Service](https://spring.io/guides/gs/reactive-rest-service/)
* [Building a GraphQL service](https://spring.io/guides/gs/graphql-server/)
* [Messaging with Redis](https://spring.io/guides/gs/messaging-redis/)
* [Accessing Data with MongoDB](https://spring.io/guides/gs/accessing-data-mongodb/)
* [Using WebSocket to build an interactive web application](https://spring.io/guides/gs/messaging-stomp-websocket/)
* [Validation](https://spring.io/guides/gs/validating-form-input/)
* [Building a RESTful Web Service with Spring Boot Actuator](https://spring.io/guides/gs/actuator-service/)

### Additional Links
These additional references should also help you:

* [Configure AOT settings in Build Plugin](https://docs.spring.io/spring-boot/docs/3.1.0/maven-plugin/reference/htmlsingle/#aot)

### Docker Compose support

This project contains a Docker Compose file named `compose.yaml`.

In this file, the following services have been defined:

| Service name | Image   | Tag      | Website                                   |
| ------------ | ------- | -------- | ----------------------------------------- |
| mongodb      | `mongo` | `latest` | [Website](https://hub.docker.com/_/mongo) |
| redis        | `redis` | `latest` | [Website](https://hub.docker.com/_/redis) |


Please review the tags of the used images and set them to the same as you're running in production.

## GraalVM Native Support

This project has been configured to let you generate either a lightweight container or a native executable.
It is also possible to run your tests in a native image.

### Lightweight Container with Cloud Native Buildpacks
If you're already familiar with Spring Boot container images support, this is the easiest way to get started.
Docker should be installed and configured on your machine prior to creating the image.

To create the image, run the following goal:

```
$ ./mvnw spring-boot:build-image -Pnative
```

Then, you can run the app like any other container:

```
$ docker run --rm demo:0.0.1-SNAPSHOT
```

### Executable with Native Build Tools
Use this option if you want to explore more options such as running your tests in a native image.
The GraalVM `native-image` compiler should be installed and configured on your machine.

NOTE: GraalVM 22.3+ is required.

To create the executable, run the following goal:

```
$ ./mvnw native:compile -Pnative
```

Then, you can run the app as follows:
```
$ target/demo
```

You can also run your existing tests suite in a native image.
This is an efficient way to validate the compatibility of your application.

To run your existing tests in a native image, run the following goal:

```
$ ./mvnw test -PnativeTest
```

