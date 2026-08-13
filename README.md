# Microservicio Hello

Proyecto Spring Boot mínimo con un endpoint `/hello` que devuelve un saludo.

Construir localmente (necesitas Java 17 y Gradle o usar la imagen Docker builder):

```bash
./gradlew build
java -jar build/libs/hello-service-0.0.1-SNAPSHOT.jar
```

Construir la imagen Docker:

```bash
docker build -t hello-service:latest .
docker run -p 8080:8080 hello-service:latest
```

Endpoint: `http://localhost:8080/hello` -> devuelve `Hola Zephuyrum`
