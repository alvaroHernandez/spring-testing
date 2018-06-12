#! /bin/bash

docker stop testing-example-postgres
docker rm testing-example-postgres
docker run --name testing-example-postgres -p 15432:5432 -e POSTGRES_PASSWORD=password -e POSTGRES_USER=testuser -d postgres
