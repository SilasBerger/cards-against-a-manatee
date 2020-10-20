cd web-client
npm run build
cd ..
cd server
./mvnw clean package
cd ..
docker-compose build