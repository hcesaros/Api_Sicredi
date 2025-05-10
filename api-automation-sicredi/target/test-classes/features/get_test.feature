#language: pt

#Autor: Henrique Cesar
#Data: 09/05/2025

Funcionalidade: Verificar status da API com GET /test

  Cenario: Validar resposta de sucesso do endpoint /test
    Dado eu envio uma requisição GET para "/test"
    Entao o código de status da resposta deve ser 200
    E o corpo da resposta deve conter os seguintes campos:
      | status | ok  |
      | method | GET |
