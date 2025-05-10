#language: pt

#Autor: Henrique Cesar
#Data: 09/05/2025


Funcionalidade: Autenticação de usuários


  Cenario: Login com credenciais válidas
    Quando envio uma requisição POST para "/auth/login" com os dados:
      | username | emilys     |
      | password | emilyspass |
    Entao o código de status da resposta deve ser 200
    E o campo "id" na resposta deve ser "1"
    E o campo "username" na resposta deve ser "emilys"
    E o campo "email" na resposta deve ser "emily.johnson@x.dummyjson.com"
    E o campo "firstName" na resposta deve ser "Emily"
    E o campo "lastName" na resposta deve ser "Johnson"
    E o campo "gender" na resposta deve ser "female"
    E o campo "image" na resposta deve ser "https://dummyjson.com/icon/emilys/128"
    E a resposta deve conter um token valido
    E a resposta deve conter o campo "refreshToken"

  Cenario: Login com senha inválida
    Dado que o payload de login possui username "emilys" e password "senha_incorreta"
    Quando envio uma requisição POST para "/auth/login"
    Então o código de status da resposta deve ser 400

  Cenario: Login com username inexistente
    Dado que o payload de login possui username "usuario_falso" e password "senha123"
    Quando envio uma requisição POST para "/auth/login"
    Então o código de status da resposta deve ser 400

  Cenario: Login com corpo vazio
    Dado que o payload de login está vazio
    Quando envio uma requisição POST para "/auth/login"
    Então o código de status da resposta deve ser 400
