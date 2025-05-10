#language: pt

#Autor: Henrique Cesar
#Data: 09/05/2025

Funcionalidade: Consultar usuários - GET /users

  Cenario: Validar status da resposta da API
    Dado eu envio uma requisição GET para "/users"
    Entao o código de status da resposta deve ser 200

  Cenario: Verificar a estrutura e dados completos dos usuários retornados
    Dado eu envio uma requisição GET para "/users"
    Entao o código de status da resposta deve ser 200
    E a resposta deve conter uma lista com pelo menos 2 usuários

        # ---------- Validação completa do usuário 0 (Emily Johnson) ----------
    E o usuário na posição 0 deve conter os seguintes dados:
      | id          | 1                                   |
      | firstName   | Emily                               |
      | lastName    | Johnson                             |
      | maidenName  | Smith                               |
      | age         | 28                                  |
      | gender      | female                              |
      | email       | emily.johnson@x.dummyjson.com       |
      | phone       | +81 965-431-3024                    |
      | username    | emilys                              |
      | password    | emilyspass                          |
      | birthDate   | 1996-5-30                           |
      | image       | https://dummyjson.com/icon/emilys/128 |
      | bloodGroup  | O-                                  |
      | height      | 193.24                              |
      | weight      | 63.16                               |
      | eyeColor    | Green                               |
      | domain      |                                     |
      | ip          | 42.48.100.32                        |
      | macAddress  | 47:fa:41:18:ec:eb                   |
      | university  | University of Wisconsin--Madison    |
      | ein         | 977-175                             |
      | ssn         | 900-590-289                         |
      | userAgent   | Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/96.0.4664.93 Safari/537.36 |

    E o campo "hair.color" do usuário 0 deve ser "Brown"
    E o campo "hair.type" do usuário 0 deve ser "Curly"

    E o campo "address.address" do usuário 0 deve ser "626 Main Street"
    E o campo "address.city" do usuário 0 deve ser "Phoenix"
    E o campo "address.state" do usuário 0 deve ser "Mississippi"
    E o campo "address.postalCode" do usuário 0 deve ser "29112"
    E o campo "address.coordinates.lat" do usuário 0 deve conter uma coordenada válida
    E o campo "address.coordinates.lng" do usuário 0 deve conter uma coordenada válida

    E o campo "bank.cardExpire" do usuário 0 deve ser "03/26"
    E o campo "bank.cardNumber" do usuário 0 deve ser "9289760655481815"
    E o campo "bank.cardType" do usuário 0 deve ser "Elo"
    E o campo "bank.currency" do usuário 0 deve ser "CNY"
    E o campo "bank.iban" do usuário 0 deve ser "YPUXISOBI7TTHPK2BR3HAIXL"

    E o campo "company.name" do usuário 0 deve ser "Dooley, Kozey and Cronin"
    E o campo "company.department" do usuário 0 deve ser "Engineering"
    E o campo "company.title" do usuário 0 deve ser "Sales Manager"
    E o campo "company.address.address" do usuário 0 deve ser "263 Tenth Street"
    E o campo "company.address.city" do usuário 0 deve ser "San Francisco"
    E o campo "company.address.state" do usuário 0 deve ser "Wisconsin"
    E o campo "company.address.postalCode" do usuário 0 deve ser "37657"
    E o campo "company.address.coordinates.lat" do usuário 0 deve conter uma coordenada válida
    E o campo "company.address.coordinates.lng" do usuário 0 deve conter uma coordenada válida

        # ---------- Validação completa do usuário 1 (Michael Williams) ----------
    E o usuário na posição 1 deve conter os seguintes dados:
      | id          | 2                                   |
      | firstName   | Michael                             |
      | lastName    | Williams                            |
      | maidenName  |                                     |
      | age         | 35                                  |
      | gender      | male                                |
      | email       | michael.williams@x.dummyjson.com    |
      | phone       | +49 258-627-6644                    |
      | username    | michaelw                            |
      | password    | michaelwpass                        |
      | birthDate   | 1989-8-10                           |
      | image       | https://dummyjson.com/icon/michaelw/128 |
      | bloodGroup  | B+                                  |
      | height      | 186.22                              |
      | weight      | 76.32                               |
      | eyeColor    | Red                                 |
      | domain      |                                     |
      | ip          | 12.13.116.142                       |
      | macAddress  | 79:15:78:99:60:aa                   |
      | university  | Ohio State University               |
      | ein         | 912-602                             |
      | ssn         | 108-953-962                         |
      | userAgent   | Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Edge/97.0.1072.76 Safari/537.36 |

    E o campo "hair.color" do usuário 1 deve ser "Green"
    E o campo "hair.type" do usuário 1 deve ser "Straight"

    E o campo "address.address" do usuário 1 deve ser "385 Fifth Street"
    E o campo "address.city" do usuário 1 deve ser "Houston"
    E o campo "address.state" do usuário 1 deve ser "Alabama"
    E o campo "address.postalCode" do usuário 1 deve ser "38807"
    E o campo "address.coordinates.lat" do usuário 1 deve conter uma coordenada válida
    E o campo "address.coordinates.lng" do usuário 1 deve conter uma coordenada válida

    E o campo "bank.cardExpire" do usuário 1 deve ser "02/27"
    E o campo "bank.cardNumber" do usuário 1 deve ser "6737807858721625"
    E o campo "bank.cardType" do usuário 1 deve ser "Elo"
    E o campo "bank.currency" do usuário 1 deve ser "SEK"
    E o campo "bank.iban" do usuário 1 deve ser "83IDT77FWYLCJVR8ISDACFH0"

    E o campo "company.name" do usuário 1 deve ser "Spinka - Dickinson"
    E o campo "company.department" do usuário 1 deve ser "Support"
    E o campo "company.title" do usuário 1 deve ser "Support Specialist"
    E o campo "company.address.address" do usuário 1 deve ser "395 Main Street"
    E o campo "company.address.city" do usuário 1 deve ser "Los Angeles"
    E o campo "company.address.state" do usuário 1 deve ser "New Hampshire"
    E o campo "company.address.postalCode" do usuário 1 deve ser "73442"
    E o campo "company.address.coordinates.lat" do usuário 1 deve conter uma coordenada válida
    E o campo "company.address.coordinates.lng" do usuário 1 deve conter uma coordenada válida

  Cenário: Enviar requisição com parâmetro inválido e validar erro 400
    Dado eu envio uma requisição GET para "/users?limit=abc"
    Entao o código de status da resposta deve ser 400
    E a mensagem de erro deve conter "Bad Request" ou similar

