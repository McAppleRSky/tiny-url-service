docker daemon start:
sudo /etc/init.d/docker start

docker build . --tag=tiny-url:latest

docker run -p80:8080 tiny-url:latest

docker inspect tiny-url
docker stop tiny-url
docker rm tiny-url
