#docker build \
#  --build-arg SERVICE_NAME=user-service \
#  -t user-service:0.0.1-SNAPSHOT .

docker buildx build --platform linux/amd64 \
  --build-arg SERVICE_NAME=user-service \
  -t northamerica-northeast2-docker.pkg.dev/cpsc-5207el-06-microservice/user-service/user-service:latest \
  . \
  --push