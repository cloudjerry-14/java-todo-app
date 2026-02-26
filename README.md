Java Todo App
=================

This repository contains a simple Spring Boot Todo application (Java 17, Thymeleaf views, H2 in-memory DB).

CI / CD
-------

A GitHub Actions workflow is included at `.github/workflows/ci.yml` that:

- Runs on push / PR to `main`.
- Builds and runs tests with Maven.
- Builds a Docker image and pushes it to Docker Hub.

Secrets required (set these in the GitHub repository settings -> Secrets -> Actions):

- `DOCKERHUB_USERNAME` – your Docker Hub username
- `DOCKERHUB_TOKEN` – a Docker Hub access token (or password). A token is recommended.

For automatic deployment to an EC2 host the workflow needs the following additional secrets:

- `EC2_HOST` – public IP or DNS name of your EC2 instance
- `EC2_USER` – SSH user (for example `ec2-user` for Amazon Linux, `ubuntu` for Ubuntu AMIs)
- `EC2_SSH_KEY` – the private SSH key (PEM) content for the above user (add as a secret value)
- `EC2_SSH_PORT` – optional SSH port (defaults to 22 if not set)

Published image name
--------------------

The workflow pushes images with these tags:

- `${{ secrets.DOCKERHUB_USERNAME }}/my-app:latest`
- `${{ secrets.DOCKERHUB_USERNAME }}/my-app:${{ github.sha }}`

How to run locally
-------------------

1. Build and run with Maven:

```bash
mvn spring-boot:run
```

2. Or build and run the jar:

```bash
mvn -DskipTests package
java -jar target/my-app-0.0.1-SNAPSHOT.jar
```

3. Docker (if you have Docker locally):

```bash
docker build -t my-app .
docker run -p 8080:8080 my-app
```

Web UI: http://localhost:8080/todos

H2 Console: http://localhost:8080/h2-console (JDBC URL: `jdbc:h2:mem:todos`)



======
Let's try
