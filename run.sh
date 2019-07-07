#!/usr/bin/env bash

mvn deploy

java -jar \
 target/dropwizard-kotlin-app.jar \
 server \
 src/main/resources/app-config.yml
