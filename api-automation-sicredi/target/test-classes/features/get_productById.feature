#language: pt

#Autor: Henrique Cesar
#Data: 09/05/2025


Funcionalidade: Buscar apenas um produto por id

  Cenário: Consultar produto com ID 1 e validar todos os campos do response
    Dado eu envio uma requisição GET para "/products/1"
    Entao o corpo da resposta deve conter todos os campos esperados do produto

  Cenário: Consultar produto inexistente e validar mensagem de erro
    Dado eu envio uma requisição GET para "/products/0"
    Entao o código de status da resposta deve ser 404
    E o corpo da resposta deve conter a mensagem "Product with id '0' not found"