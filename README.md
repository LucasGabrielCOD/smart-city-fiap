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
## Testes

### Testes Automatizados

Os testes automatizados são executados no workflow de staging para garantir a qualidade do código antes da implantação em produção.

* **Tipos de testes:** Testes de endpoint utilizando JUnit e Mockito.
* **Execução dos testes:** Os testes são executados automaticamente a cada push na branch `teste2-final`.
* **Localização dos testes:** Os testes estão localizados na pasta `src/test/java`.
* **Relatórios de teste:** Os relatórios de teste podem ser encontrados na aba "Actions" do repositório no GitHub, na execução do workflow "Build and deploy JAR app to Azure Web App - smart-city-staging".

### Execução de Testes Localmente

Para executar os testes localmente, você pode usar o Maven:

1. **Navegue até o diretório raiz do projeto:**
   ```bash
   cd /caminho/para/o/seu/projeto/smart-city-fiap
   ```
2 **Execute o comando:**
   ```bash
    mnv test
   ```
### Descrição dos Testes

**Testes do `AccidentControllerTest`:**

**testGetAllAccidents():**
* Verifica se o endpoint GET /api/accidents retorna uma lista de todos os acidentes com sucesso.
* Mocka o AccidentService para retornar uma lista de dois acidentes.
* Verifica se o status code da resposta é 200 (OK).
* Verifica se o corpo da resposta contém uma lista com dois acidentes.

**testCreateAccident():**
* Verifica se o endpoint POST /api/accidents cria um novo acidente com sucesso.
* Mocka o AccidentService para salvar o acidente.
* Verifica se o status code da resposta é 201 (Created).
* Verifica se o corpo da resposta contém o acidente criado, com a localização correta.

**testUpdateAccident():**
* Verifica se o endpoint PUT /api/accidents/{id} atualiza um acidente existente com sucesso.
* Mocka o AccidentService para atualizar o acidente.
* Verifica se o status code da resposta é 200 (OK).
* Verifica se o corpo da resposta contém o acidente atualizado, com a localização correta.

**testDeleteAccident():**
* Verifica se o endpoint DELETE /api/accidents/{id} exclui um acidente com sucesso.
* Mocka o AccidentService para excluir o acidente.
* Verifica se o status code da resposta é 204 (No Content).

**testGetAccidentById_NotFound():**
* Verifica se o endpoint GET /api/accidents/{id} retorna um erro 404 (Not Found) quando o acidente não é encontrado.
* Mocka o AccidentService para lançar uma ResponseStatusException com status 404.
* Verifica se o status code da resposta é 404 (Not Found).

**testUpdateAccident_BadRequest():**
* Verifica se o endpoint PUT /api/accidents/{id} retorna um erro 400 (Bad Request) quando a requisição é inválida.
* Envia uma requisição com corpo vazio.
* Verifica se o status code da resposta é 400 (Bad Request).


**Testes do `AccidentServiceTest`:**

**testSaveAccident():**
* Verifica se o método saveAccident do AccidentService salva um acidente com sucesso.
* Mocka o AccidentRepository para simular o salvamento do acidente.
* Valida o corpo da resposta com o schema JSON definido.
* Verifica se o método save do AccidentRepository foi chamado uma vez.

**testUpdateAccident():**
* Verifica se o método updateAccident do AccidentService atualiza um acidente com sucesso.
* Mocka o AccidentRepository para simular a busca e o salvamento do acidente.
* Valida o corpo da resposta com o schema JSON definido.
* Verifica se o método save do AccidentRepository foi chamado uma vez.

**testDeleteAccident():**
* Verifica se o método deleteAccident do AccidentService exclui um acidente com sucesso.
* Mocka o AccidentRepository para simular a exclusão do acidente.
* Verifica se o método deleteById do AccidentRepository foi chamado uma vez.

**Observações:**

*   Os testes utilizam o framework JUnit e a biblioteca Mockito para simular o comportamento dos componentes externos (como o `AccidentService` e o `AccidentRepository`).
*   Os testes verificam se os métodos dos controllers e services retornam os valores esperados e se interagem corretamente com os repositórios.
*   É importante ter uma boa cobertura de testes para garantir a qualidade do código e evitar bugs.



   
