docker container stop manatee-server
mvn clean compile package
docker build -t manatee/server .
docker container run --rm --name manatee-server -p 8080:8080 -d -e CARD_PACKS_BASE_PATH=/etc/cards manatee/server
