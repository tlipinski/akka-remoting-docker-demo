#!/bin/bash

last_container_id=$(sudo docker ps -lq)
remote_container_ip=$(sudo docker inspect -f "{{.NetworkSettings.IPAddress}}" $last_container_id)

sudo docker run --add-host remote-host:$remote_container_ip --env REMOTE_ACTOR_HOSTNAME=remote-host akka-remoting-docker-demo/local
