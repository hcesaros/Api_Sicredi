#language: pt

#Autor: Henrique Cesar
#Data: 09/05/2025


Funcionalidade: Validação completa do endpoint de produtos


  Cenário: Validar todos os campos do response de produtos
    Dado eu envio uma requisição GET para "/products"
    E o código de status da resposta deve ser 200
    Entao valido todos os campos do response de produtos

  Cenário: Requisição para endpoint inexistente
    Dado eu envio uma requisição GET para "/productserro"
    Entao o código de status da resposta deve ser 404

  Cenário: Requisição com token inválido
    Dado que o payload de login possui username "emilys" e password "emilyspass"
    Quando envio uma requisição GET para "/auth/productserro" sem token
    Entao o código de status da resposta deve ser 401
    E o corpo da resposta deve conter a mensagem "Access Token is required"

  Cenário: Requisição para endpoint inexistente
    Dado que o payload de login possui username "emilys" e password "emilyspass"
    Quando envio uma requisição POST para "/auth/login"
    Entao o código de status da resposta deve ser 200
    E a resposta deve conter um token valido

    Quando envio uma requisição GET autenticada para "/auth/productsXYZ"
    Entao o código de status da resposta deve ser 404