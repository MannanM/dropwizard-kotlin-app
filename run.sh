#!/usr/bin/env bash
############################
# Usage: run.sh [-d] [-j]
# -d: Run with docker
# -j: Run jar directly
############################

APP_NAME=dropwizard-kotlin-app

echo "Building ${APP_NAME}.jar"
mvn deploy

if [[ "$1" == "-d" ]]; then
 echo "Building ${APP_NAME} docker image"
 docker build -t ${APP_NAME} .
 echo "Running docker image and exposing port 8080"
 docker run -p 8080:8080 -it --rm ${APP_NAME}:latest
else
 echo "Executing jar"
 java -jar target/${APP_NAME}.jar server src/main/resources/app-config.yml
fi
