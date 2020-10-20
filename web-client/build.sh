#!/bin/sh

# Sanity check: Make sure that we're in the right directory.
if ! (ls VERSION > /dev/null 2>&1 && ls IMAGE_NAME > /dev/null 2>&1)
then
  echo "This script needs to be run from within its parent directory."
  exit 1;
fi

# Read base image name and version and assemble tag name.
BASE_IMAGE_NAME=$(head -n 1 IMAGE_NAME | sed "s/\\s*//g")
VERSION=$(head -n 1 VERSION | sed "s/\\s*//g")
TAG=${BASE_IMAGE_NAME}:${VERSION}

# Kill and remove any running containers of current image.
RUNNING_CONTAINERS=$(docker ps -a -q  --filter ancestor="${TAG}")
docker container stop "${RUNNING_CONTAINERS}"
docker container rm "${RUNNING_CONTAINERS}"

# Remove old image with same tag to prevent untagged orphans.
docker image rm "${TAG}"
docker image rm "${BASE_IMAGE_NAME}:latest"

# Build Angular application
echo "Building Angular application"
if ! ng build --prod
then
  echo "Angular build failed"
  exit 1
fi

# Build Docker image.
echo "Building Docker image '${TAG}'"
if docker build -t "${TAG}" .
then
  echo "Success!"
else
  echo "Docker build failed"
fi

# Tag image as latest
docker tag "${TAG}" "${BASE_IMAGE_NAME}:latest"
