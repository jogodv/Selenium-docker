FROM bellsoft/liberica-openjre-alpine:17.0.8

#add utilities

RUN apk add curl jq

#workspace
WORKDIR /home/selenium-docker

#add required files
ADD target/docker-resources ./
ADD runner.sh               runner.sh

#run tests and give env varaiables
RUN dos2unix runner.sh

ENTRYPOINT sh runner.sh
