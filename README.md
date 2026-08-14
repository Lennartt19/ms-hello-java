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

Endpoint: `http://localhost:8080/hello` -> devuelve `Hola Leonardo`

Endpoint adicional `/secreto` (opcional):

Ejemplo local (fallback):

```bash
export SECRET_VALUE="mi-secreto-local"
./gradlew bootRun
curl http://localhost:8080/secreto
```

Mostrar variables en `/secreto`:

- Puedes configurar qué variables de entorno se muestran mediante la propiedad `secreto.vars` en `src/main/resources/application.properties` o exportando como variable de entorno `SECRETO_VARS`. Ejemplo:

```bash
export SECRETO_VARS="SECRET_VALUE,APP_NAME,APP_VERSION,USER,HOSTNAME"
export APP_NAME="hello-service"
export APP_VERSION="1.0.0"
export SECRET_VALUE="mi-secreto-local"
./gradlew bootRun
curl http://localhost:8080/secreto
```

La respuesta incluirá las variables solicitadas y metadatos (`secret`, `source`, `timestamp`).

