#!/bin/bash

echo "Valor do argumento: $1"

# Verifica se o ambiente foi especificado
if [ -z "$1" ]; then
  echo "Uso: $0 <ambiente>"
  echo "Onde <ambiente> pode ser 'staging' ou 'production'"
  exit 1
fi

# Define o ambiente
export ambiente=$1  # Exportar a variável

echo "Valor da variável ambiente: $ambiente"

# Define as variáveis de ambiente de acordo com o ambiente
if [ "$ambiente" = "staging" ]; then
  export SPRING_PROFILES_ACTIVE=staging
  service=smart-city-staging
elif [ "$ambiente" = "production" ]; then
  export SPRING_PROFILES_ACTIVE=production
  service=smart-city-production
else
  echo "Ambiente inválido: $ambiente"
  exit 1
fi

# Inicia o serviço correspondente ao ambiente
docker-compose up -d $service