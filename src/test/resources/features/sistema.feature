Funcionalidade: Sistema de Gerenciamento de Acidentes e Emergências

  Cenário: Cadastrar um acidente e uma emergência relacionada com sucesso
    Dado que eu esteja na página de cadastro de acidentes
    Quando eu preencher os dados do acidente com a localização "Avenida Paulista" e status "EM_ANDAMENTO"
    E submeter o formulário
    Então o sistema deve criar um novo acidente com os dados informados
    E eu devo ser redirecionado para a página de detalhes do acidente
    Quando eu clicar no botão "Criar Emergência"
    E preencher os dados da emergência com status "EM_ANDAMENTO"
    E submeter o formulário
    Então o sistema deve criar uma nova emergência relacionada ao acidente
    E exibir uma mensagem de sucesso

  Cenário: Tentar cadastrar um acidente com localização inválida
    Dado que eu esteja na página de cadastro de acidentes
    Quando eu preencher os dados do acidente com a localização "" (em branco) e status "EM_ANDAMENTO"
    E submeter o formulário
    Então o sistema não deve criar um novo acidente
    E deve exibir uma mensagem de erro informando que a localização é obrigatória

  Cenário: Tentar atualizar uma emergência com status inválido
    Dado que eu esteja na página de detalhes de uma emergência
    Quando eu preencher os dados da emergência com status "" (em branco)
    E submeter o formulário
    Então o sistema não deve atualizar a emergência
    E deve exibir uma mensagem de erro informando que o status é obrigatório