Funcionalidade: Gerenciar Emergências

  Cenário: Cadastrar uma nova emergência com sucesso
    Dado que exista um acidente com ID 1
    E eu envie uma requisição POST para "/api/emergencies" com os dados válidos da emergência:
      """
      {
        "accident": { "id": 1 },
        "status": "EM_ANDAMENTO"
      }
      """
    E o usuário esteja autenticado
    Quando a requisição for processada
    Então o status code da resposta deve ser 200
    E o corpo da resposta deve conter os dados da emergência cadastrada
    E o corpo da resposta deve ser válido de acordo com o schema de "Emergency"

  Cenário: Listar todas as emergências
    Dado que eu envie uma requisição GET para "/api/emergencies"
    E o usuário esteja autenticado
    Quando a requisição for processada
    Então o status code da resposta deve ser 200
    E o corpo da resposta deve conter uma lista de emergências

  Cenário: Tentar cadastrar uma emergência com dados inválidos
    Dado que eu envie uma requisição POST para "/api/emergencies" com os dados inválidos da emergência:
      """
      {
        "accident": { "id": 1 },
        "status": ""
      }
      """
    E o usuário esteja autenticado
    Quando a requisição for processada
    Então o status code da resposta deve ser 400
    E o corpo da resposta deve conter uma mensagem de erro indicando o campo inválido

  Cenário: Atualizar uma emergência existente com sucesso
    Dado que exista uma emergência com ID 1
    E eu envie uma requisição PUT para "/api/emergencies/1" com os dados válidos da emergência:
      """
      {
        "status": "CONCLUÍDO"
      }
      """
    E o usuário esteja autenticado
    Quando a requisição for processada
    Então o status code da resposta deve ser 200
    E o corpo da resposta deve conter os dados da emergência atualizada
    E o corpo da resposta deve ser válido de acordo com o schema de "Emergency"

  Cenário: Tentar atualizar uma emergência com dados inválidos
    Dado que exista uma emergência com ID 1
    E eu envie uma requisição PUT para "/api/emergencies/1" com os dados inválidos da emergência:
      """
      {
        "status": ""
      }
      """
    E o usuário esteja autenticado
    Quando a requisição for processada
    Então o status code da resposta deve ser 400
    E o corpo da resposta deve conter uma mensagem de erro indicando o campo inválido

  Cenário: Deletar uma emergência existente
    Dado que exista uma emergência com ID 1
    E eu envie uma requisição DELETE para "/api/emergencies/1"
    E o usuário esteja autenticado
    Quando a requisição for processada
    Então o status code da resposta deve ser 204
    E a emergência com ID 1 não deve mais existir