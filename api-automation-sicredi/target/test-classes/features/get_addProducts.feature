#language: pt

#Autor: Henrique Cesar
#Data: 09/05/2025


Funcionalidade: Cadastro de produto

  Como usuário da API DummyJSON
  Quero adicionar um novo produto
  Para que ele esteja disponível no catálogo

  Cenário: Adicionar um novo produto com sucesso
    Dado que o payload do produto é preenchido conforme o exemplo do curl
    Quando envio uma requisição POST para "/products/add"
    E o código de status da resposta deve ser 201
    Entao a resposta deve retornar todos os dados do produto informados no payload, junto com um campo "id" gerado automaticamente:

      | title              | Perfume Oil                                               |
      | description        | Mega Discount, Impression of A...                         |
      | price              | 13                                                        |
      | discountPercentage | 8.4                                                       |
      | rating             | 4.26                                                      |
      | stock              | 65                                                        |
      | brand              | Impression of Acqua Di Gio                                |
      | category           | fragrances                                                |
      | thumbnail          | https://i.dummyjson.com/data/products/11/thumnail.jpg     |




