#!/bin/bash

# Verifica se o ambiente foi especificado
if [ -z "$1" ]; then
  echo "Uso: $0 <ambiente>"
  echo "Onde <ambiente> pode ser 'staging' ou 'production'"
  exit 1
fi

# Define o ambiente
ambiente=$1

# Faz o pull da imagem Docker
docker pull ${{ secrets.DOCKER_USERNAME }}/smart-city:latest

# Define as variáveis de ambiente de acordo com o ambiente
if [ "$ambiente" == "staging" ]; then
  export SPRING_PROFILES_ACTIVE=staging
  service=smart-city-staging
elif [ "$ambiente" == "production" ]; then
  export SPRING_PROFILES_ACTIVE=production
  service=smart-city-production
else
  echo "Ambiente inválido: $ambiente"
  exit 1
fi

# Inicia o serviço correspondente ao ambiente
docker-compose up -d $service