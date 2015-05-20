#!/bin/bash

sudo docker run -p 2552:2552 --hostname remote-host --env HOSTNAME=remote-host akka-remoting-docker-demo/remote
