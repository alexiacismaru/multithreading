# Trains Multithreading

Command line multithreading application.

## Building and running

In order to build and run this application on your system, you need:
* JDK 17+
* Docker (docker daemon needs to be running)

### Database

The application uses a PostgreSQL database that can be set up as a Docker container. Run
the `Dockerfile` and  make sure to map PostgreSQL/container port 5432 to host **port 5433**.
As you can see in `application.properties`, the Spring Boot application connects to DB port 5433.
This is because you may be running a real PostgreSQL on your system on port 5432 (as I do).

Once the DB is up and running, you can start the Spring Boot application. No Spring profile
has been set up.

#### Commands

From the project root, create the docker image. It can be created from the `Dockerfile`:
```shell
docker build -t "threading_db_image:Dockerfile" .
```

Next, create a container. Here, I'm mapping the container's port 5432 to the host's port
5433 (since 5432 is already occupied on my machine). I'm giving the container
the name `threading_db_container`:
```shell
docker create --name threading_db_container -p 5433:5432 threading_db_image:Dockerfile
```

Start the container:
```shell
docker container start threading_db_container
```

Optional: enable auto-start for this container:
```shell
docker update --restart unless-stopped threading_db_container
```

### Spring Boot Application

#### Commands

```shell
./gradlew bootRun
```

### Amount of time it took for the single threaded seeding in nanos: 900.
### Amount of time it took for the multithreaded seeding in nanos:
- N=2 -> 1300
- N=4 -> 1300
- N=8 -> 1700
- N=16 -> 1500

### Amount of time it took for the single threaded searching in nanos: 1400
### Amount of time it took for the multithreaded searching in nanos:
- N=2 -> 1200
- N=4 -> 1300
- N=8 -> 1300
- N=16 -> 1500
