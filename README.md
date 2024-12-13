# bills-rest-server


For local testing

```bash
  mvn clean test -Dspring.profiles.active=test
```


Activate Profiles Based on Environment
Spring Boot can use the spring.profiles.active property to determine which profile-specific configuration to load.


```bash
mvn spring-boot:run -Dspring.profiles.active=local
```

For GCP deployment, the SPRING_PROFILES_ACTIVE environment variable can be set to gcp in your GCP runtime configuration.


Build the project locally:

```bash
mvn clean install
```

Deploy to GCP with the correct profile:

```bash
mvn clean package -Dspring.profiles.active=gcp
```