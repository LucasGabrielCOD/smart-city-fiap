#!/bin/bash

echo "Valor do argumento: $1"

# Define as variáveis de ambiente de acordo com o argumento
if [ "$1" = "staging" ]; then
  export SPRING_PROFILES_ACTIVE=staging
  service=smart-city-staging
  # Define as variáveis de ambiente para staging
  export DB_URL=${DB_URL_STAGING}
  export DB_USER=${DB_USER_STAGING}
  export DB_PASSWORD=${DB_PASSWORD_STAGING}
elif [ "$1" = "production" ]; then
  export SPRING_PROFILES_ACTIVE=production
  service=smart-city-production
  # Define as variáveis de ambiente para production
  export DB_URL=${DB_URL_PRODUCTION}
  export DB_USER=${DB_USER_PRODUCTION}
  export DB_PASSWORD=${DB_PASSWORD_PRODUCTION}
else
  echo "Ambiente inválido: $1"
  exit 1
fi

# Inicia o serviço correspondente ao ambiente
docker-compose up -d $service