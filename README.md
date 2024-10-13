# Smart Cities - Monitoramento de Acidentes e Emergências

Este projeto visa criar uma API REST para gerenciar informações sobre acidentes e emergências em cidades inteligentes. O sistema permite registrar novos acidentes, monitorar o status de emergências relacionadas e fornecer informações em tempo real para auxiliar na tomada de decisões e melhorar o tempo de resposta a incidentes.

## Funcionalidades

- **Cadastro de Acidentes:** Permite registrar novos acidentes, incluindo localização e status.
- **Gerenciamento de Emergências:** Permite criar e acompanhar emergências relacionadas a acidentes, com informações sobre status e tempo de resposta.
- **API REST:** Fornece endpoints para acessar e manipular os dados de acidentes e emergências.

## Tecnologias Utilizadas

- **Java 17:** Linguagem de programação principal.
- **Spring Boot:** Framework para desenvolvimento de aplicações web.
- **Maven:** Ferramenta de gerenciamento de dependências e build.
- **Docker:** Plataforma de containerização para facilitar o desenvolvimento e deploy.
- **Oracle Database:** Banco de dados para armazenar as informações.
- **GitHub Actions:** Plataforma de CI/CD para automatizar o processo de build, teste e deploy da aplicação no Docker Hub.

## Como Executar o Projeto

### Pré-requisitos

- Docker e Docker Compose instalados.
- Uma instância do Oracle Database acessível, com as credenciais de acesso configuradas.
- Git instalado para clonar o repositório do projeto.

### Clonar o Repositório

1. Abra o terminal e execute o seguinte comando:

   ```bash
   git clone https://github.com/LucasGabrielCOD/smart-city-fiap.git
   ```
## Configuração do Ambiente

Este projeto utiliza `SECRETS` do GitHub Actions para armazenar as variáveis de ambiente sensíveis, como as credenciais do banco de dados.

**Para configurar o ambiente de staging e production no GitHub Actions:**

1. Vá em "Settings" > "Secrets and variables" > "Actions" no seu repositório.
2. Crie os seguintes segredos:

   **Staging:**
    * `DB_URL_STAGING`: URL de conexão com o banco de dados de staging.
    * `DB_USER_STAGING`: Nome de usuário do banco de dados de staging.
    * `DB_PASSWORD_STAGING`: Senha do banco de dados de staging.

   **Production:**
    * `DB_URL_PRODUCTION`: URL de conexão com o banco de dados de production.
    * `DB_USER_PRODUCTION`: Nome de usuário do banco de dados de production.
    * `DB_PASSWORD_PRODUCTION`: Senha do banco de dados de production.

3. Preencha cada `secret` com o valor correspondente.

**Observação:**

As variáveis de ambiente definidas no GitHub Actions serão utilizadas automaticamente pelo workflow durante o deploy.

## Build da imagem Docker
1. Navegue até o diretório raiz do projeto:

   ```bash
   cd /caminho/para/o/seu/projeto/smart-city-fiap
   ```

   (substitua /caminho/para/o/seu/projeto/ pelo caminho real do seu projeto. No meu caso ficará /home/lucas/Área de trabalho/laboratorio/ProjetoSmartCities)


2. Execute o comando:

    ```bash
   docker build -t zephyryuusha/smart-city .
   ```

Este comando irá construir a imagem Docker da aplicação, utilizando o Dockerfile na raiz do projeto.

## Execução com Docker Compose
1. Execute o comando:

   ```bash
   docker-compose up -d
   ```

Este comando irá iniciar os serviços definidos no docker-compose.yml em modo detached (background), incluindo a aplicação e o banco de dados (se configurado).

## Acessando os Endpoints da API
A API estará disponível através da interface do Swagger UI, que facilita a visualização e interação com os endpoints.

- **Ambiente de Produção:** Acesse a API em http://localhost:8080/swagger-ui.html quando o Docker Compose estiver em execução em ambiente de produção.
- **Ambiente de Staging:** Para o ambiente de staging, a API estará disponível em http://localhost:8081/swagger-ui.html

### Observação:

As portas 8080 e 8081 são as portas padrão configuradas no docker-compose.yml. Caso você queira modifcar essas portas no arquivo de configuração, ajuste os endereços acima de acordo.

## Endpoints:
- **GET /api/accidents:** Retorna uma lista de todos os acidentes.
- **POST /api/accidents:** Cria um novo acidente.
- **PUT /api/accidents/{id}:** Atualiza um acidente existente.
- **DELETE /api/accidents/{id}:** Exclui um acidente.
- **GET /api/emergencies:** Retorna uma lista de todas as emergências.
- **POST /api/emergencies:** Cria uma nova emergência.
- **PUT /api/emergencies/{id}:** Atualiza uma emergência existente.
- **DELETE /api/emergencies/{id}:** Exclui uma emergência.

### Exemplo de uso com curl:
Para obter a lista de todos os acidentes:
```bash
curl http://localhost:8080/api/accidents 
```



   
