#!/bin/bash

echo "Valor do argumento: $1"

# Define as variáveis de ambiente de acordo com o argumento
if [ "$1" = "staging" ]; then
  export SPRING_PROFILES_ACTIVE=staging
  service=smart-city-staging
elif [ "$1" = "production" ]; then
  export SPRING_PROFILES_ACTIVE=production
  service=smart-city-production
else
  echo "Ambiente inválido: $1"
  exit 1
fi

# Inicia o serviço correspondente ao ambiente
docker-compose up -d $service