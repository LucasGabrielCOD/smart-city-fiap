Funcionalidade: Gerenciar Acidentes

  Cenário: Cadastrar um novo acidente com sucesso
    Dado que eu envie uma requisição POST para "/api/accidents" com os dados válidos do acidente:
      """
      {
        "location": "Rua X",
        "status": "EM_ANDAMENTO"
      }
      """
    E o usuário esteja autenticado
    Quando a requisição for processada
    Então o status code da resposta deve ser 201
    E o corpo da resposta deve conter os dados do acidente cadastrado
    E o corpo da resposta deve ser válido de acordo com o schema de "Accident"

  Cenário: Listar todos os acidentes
    Dado que eu envie uma requisição GET para "/api/accidents"
    E o usuário esteja autenticado
    Quando a requisição for processada
    Então o status code da resposta deve ser 200
    E o corpo da resposta deve conter uma lista de acidentes

  Cenário: Tentar cadastrar um acidente com dados inválidos
    Dado que eu envie uma requisição POST para "/api/accidents" com os dados inválidos do acidente:
      """
      {
        "location": "",
        "status": "EM_ANDAMENTO"
      }
      """
    E o usuário esteja autenticado
    Quando a requisição for processada
    Então o status code da resposta deve ser 400
    E o corpo da resposta deve conter uma mensagem de erro indicando o campo inválido

  Cenário: Atualizar um acidente existente com sucesso
    Dado que exista um acidente com ID 1
    E eu envie uma requisição PUT para "/api/accidents/1" com os dados válidos do acidente:
      """
      {
        "location": "Rua Y",
        "status": "CONCLUÍDO"
      }
      """
    E o usuário esteja autenticado
    Quando a requisição for processada
    Então o status code da resposta deve ser 200
    E o corpo da resposta deve conter os dados do acidente atualizado
    E o corpo da resposta deve ser válido de acordo com o schema de "Accident"

  Cenário: Tentar atualizar um acidente com dados inválidos
    Dado que exista um acidente com ID 1
    E eu envie uma requisição PUT para "/api/accidents/1" com os dados inválidos do acidente:
      """
      {
        "location": "",
        "status": "CONCLUÍDO"
      }
      """
    E o usuário esteja autenticado
    Quando a requisição for processada
    Então o status code da resposta deve ser 400
    E o corpo da resposta deve conter uma mensagem de erro indicando o campo inválido

  Cenário: Deletar um acidente existente
    Dado que exista um acidente com ID 1
    E eu envie uma requisição DELETE para "/api/accidents/1"
    E o usuário esteja autenticado
    Quando a requisição for processada
    Então o status code da resposta deve ser 204
    E o acidente com ID 1 não deve mais existir