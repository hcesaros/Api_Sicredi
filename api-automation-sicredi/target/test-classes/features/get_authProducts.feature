#language: pt

#Autor: Henrique Cesar
#Data: 09/05/2025


Funcionalidade: Consulta completa de produtos autenticados

  Cenário: Validar todos os campos do response de produtos autenticados
   Dado que o payload de login possui username "emilys" e password "emilyspass"
    Quando envio uma requisição POST para "/auth/login"
    Entao o código de status da resposta deve ser 200
    E a resposta deve conter um token valido

    Quando envio uma requisição GET autenticada para "/auth/products"
    Entao o código de status da resposta deve ser 200

    E o corpo da resposta deve conter os seguintes dados do produto na posição 0:
      | id                    | 1                                                                 |
      | title                 | Essence Mascara Lash Princess                                     |
      | description           | The Essence Mascara Lash Princess is a popular mascara known for its volumizing and lengthening effects. Achieve dramatic lashes with this long-lasting and cruelty-free formula. |
      | category              | beauty                                                            |
      | price                 | 9.99                                                              |
      | discountPercentage    | 10.48                                                             |
      | rating                | 2.56                                                              |
      | stock                 | 99                                                                |
      | brand                 | Essence                                                           |
      | sku                   | BEA-ESS-ESS-001                                                   |
      | weight                | 4                                                                 |
      | warrantyInformation   | 1 week warranty                                                   |
      | shippingInformation   | Ships in 3-5 business days                                        |
      | availabilityStatus    | In Stock                                                         |
      | returnPolicy          | No return policy                                                  |
      | minimumOrderQuantity  | 48                                                                |
      | meta.barcode          | 5784719087687                                                     |
      | meta.qrCode           | https://cdn.dummyjson.com/public/qr-code.png                     |
      | thumbnail             | https://cdn.dummyjson.com/product-images/beauty/essence-mascara-lash-princess/thumbnail.webp |

    E o corpo da resposta deve conter os seguintes dados do produto na posição 1:
      | id                    | 2                                                                 |
      | title                 | Eyeshadow Palette with Mirror                                     |
      | description           | The Eyeshadow Palette with Mirror offers a versatile range of eyeshadow shades for creating stunning eye looks. With a built-in mirror, it's convenient for on-the-go makeup application. |
      | category              | beauty                                                            |
      | price                 | 19.99                                                             |
      | discountPercentage    | 18.19                                                             |
      | rating                | 2.86                                                              |
      | stock                 | 34                                                                |
      | brand                 | Glamour Beauty                                                    |
      | sku                   | BEA-GLA-EYE-002                                                   |
      | weight                | 9                                                                 |
      | warrantyInformation   | 1 year warranty                                                   |
      | shippingInformation   | Ships in 2 weeks                                                  |
      | availabilityStatus    | In Stock                                                         |
      | returnPolicy          | 7 days return policy                                              |
      | minimumOrderQuantity  | 20                                                                |
      | meta.barcode          | 9170275171413                                                     |
      | meta.qrCode           | https://cdn.dummyjson.com/public/qr-code.png                     |
      | thumbnail             | https://cdn.dummyjson.com/product-images/beauty/eyeshadow-palette-with-mirror/thumbnail.webp |

  Cenário: Requisição com ausência de token deve retornar erro 401
    Dado que o payload de login possui username "emilys" e password "emilyspass"
    Quando envio uma requisição GET para "/auth/products" sem token
    Entao o código de status da resposta deve ser 401
    E o corpo da resposta deve conter a mensagem "Access Token is required"



