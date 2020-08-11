BASE_IMAGE_NAME=$(head -n 1 IMAGE_NAME | sed "s/\\s*//g")
docker container run -d --rm --name cards-frontend -p 8082:80 "${BASE_IMAGE_NAME}:latest"
