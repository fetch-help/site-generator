# site-generator
Static product catalog generator

Generate a static website given a product catalog

Then

1. Start the shopping cart server using platform-web
2. Start the docker nginx container

sudo docker pull nginx

run nginx for static website with custom config file

sudo docker run --rm --name docker-nginx -p 80:80 -v ~/fetch/site-generator/website:/usr/share/nginx/html -v ~/fetch/site-generator/website/nginx.conf:/etc/nginx/conf.d/default.conf -d nginx

sudo docker ps

sudo docker stop docker-nginx

sudo docker rm docker-nginx

sudo docker restart docker-nginx

## Troubleshooting

sudo docker logs docker-nginx 

Checking if your docker container can access localhost services

find your ip address
ifconfig

assume your ip address is 192.168.0.26 and the shopping cart is available at

### Cart id
http://192.168.0.26:8086/public/api/v1/cart/ip
 

### Start the Alpine container and drop into a Shell prompt.
docker container run --rm -it alpine sh

### Install the ping utility.
apk update && apk add iputils

### Ping your local network IP address (replace my IP address with yours).
ping 192.168.0.26


Modify your nginx.conf to point to 192.168.0.26 


