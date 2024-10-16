#!/bin/bash

# Verificamos se o ambiente foi especificado
if [ -z "$1" ]; then
  echo "Uso: $0 <ambiente>"
  echo "Onde <ambiente> pode ser 'staging' ou 'production'"
  exit 1
fi

# Definimos o ambiente
ambiente=$1

# Faz o pull da imagem Docker
docker pull ${{ secrets.DOCKER_USERNAME }}/smart-city:latest

# Define as variáveis de ambiente de acordo com o ambiente
if [ "$ambiente" == "staging" ]; then
  export SPRING_PROFILES_ACTIVE=staging
  export DB_URL=${{ secrets.DB_URL_STAGING }}
  export DB_USER=${{ secrets.DB_USER_STAGING }}
  export DB_PASSWORD=${{ secrets.DB_PASSWORD_STAGING }}
  service=smart-city-staging
elif [ "$ambiente" == "production" ]; then
  export SPRING_PROFILES_ACTIVE=production
  export DB_URL=${{ secrets.DB_URL_PRODUCTION }}
  export DB_USER=${{ secrets.DB_USER_PRODUCTION }}
  export DB_PASSWORD=${{ secrets.DB_PASSWORD_PRODUCTION }}
  service=smart-city-production
else
  echo "Ambiente inválido: $ambiente"
  exit 1
fi

# Inicia o serviço correspondente ao ambiente
docker-compose up -d $service