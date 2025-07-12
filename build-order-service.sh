#docker build \
#  --build-arg SERVICE_NAME=order-service \
#  -t order-service:0.0.1-SNAPSHOT .

docker buildx build --platform linux/amd64 \
  --build-arg SERVICE_NAME=order-service \
  -t northamerica-northeast2-docker.pkg.dev/cpsc-5207el-06-microservice/order-service-repo/order-service:latest \
  . \
  --push