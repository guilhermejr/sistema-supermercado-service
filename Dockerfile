FROM openjdk:11
LABEL maintainer="Guilherme Jr. <falecom@guilhermejr.net>"
ENV TZ=America/Bahia
ARG VAULT_HOST
ARG VAULT_TOKEN
ARG CONFIG_SERVER_USER
ARG CONFIG_SERVER_PASS
ENV VAULT_HOST=${VAULT_HOST}
ENV VAULT_TOKEN=${VAULT_TOKEN}
ENV CONFIG_SERVER_USER=${CONFIG_SERVER_USER}
ENV CONFIG_SERVER_PASS=${CONFIG_SERVER_PASS}
COPY sistema-supermercado-service.jar sistema-supermercado-service.jar
ENTRYPOINT ["java","-jar","/sistema-supermercado-service.jar"]
EXPOSE 9005