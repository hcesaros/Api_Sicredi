package steps;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.pt.*; // Importa os steps do Cucumber em português
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import java.util.Map;

import static org.hamcrest.Matchers.*;


public class AddProductsSteps {

    // Declaração de variáveis (não estão sendo usadas diretamente neste caso)
    private Response response;
    private String payload;

    /**
     * Step que define o conteúdo do payload para o envio do produto.
     * Esse payload será enviado no corpo da requisição POST.
     */
    @Dado("que o payload do produto é preenchido conforme o exemplo do curl")
    public void preencher_payload_conforme_curl() {
        CommonSteps.payload = "{"
                + "\"title\": \"Perfume Oil\","
                + "\"description\": \"Mega Discount, Impression of A...\","
                + "\"price\": 13,"
                + "\"discountPercentage\": 8.4,"
                + "\"rating\": 4.26,"
                + "\"stock\": 65,"
                + "\"brand\": \"Impression of Acqua Di Gio\","
                + "\"category\": \"fragrances\","
                + "\"thumbnail\": \"https://i.dummyjson.com/data/products/11/thumnail.jpg\""
                + "}";
    }

    /**
     * Step que envia uma requisição POST para o endpoint informado,
     * utilizando o payload previamente definido. A resposta é armazenada
     * na classe comum (CommonSteps) para ser validada posteriormente.
     */
    @Quando("envio uma requisição POST para o endpoint {string}")
    public void envio_requisicao_post_para_endpoint(String endpoint) {
        RequestSpecification request = RestAssured.given()
                .baseUri("https://dummyjson.com")
                .header("Content-Type", "application/json")
                .body(CommonSteps.payload); // Usa o payload armazenado

        Response response = request.post(endpoint);
        CommonSteps.setResponse(response); // Armazena a resposta para uso nos próximos steps

        // Impressão da resposta no console para depuração
        System.out.println("Status: " + response.statusCode());
        System.out.println("Body:\n" + response.getBody().asPrettyString());
    }

    /**
     * Step que valida se a resposta da API contém todos os campos informados no payload,
     * além de garantir que o campo "id" esteja presente e não seja nulo.
     */
    @Entao("a resposta deve retornar todos os dados do produto informados no payload, junto com um campo {string} gerado automaticamente:")
    public void validar_dados_do_produto_com_id(String campoId, DataTable dataTable) {
        Map<String, String> expectedData = dataTable.asMap(); // Converte a tabela de dados em mapa

        // Valida se o campo "id" (ou outro informado) está presente e não nulo
        CommonSteps.getResponse().then().body(campoId, notNullValue());

        // Valida cada campo do JSON conforme os dados esperados
        for (Map.Entry<String, String> entry : expectedData.entrySet()) {
            String campo = entry.getKey();
            String valorEsperado = entry.getValue();

            if (campo.equals("price") || campo.equals("stock")) {
                CommonSteps.getResponse().then().body(campo, equalTo(Integer.parseInt(valorEsperado)));
            } else if (campo.equals("rating") || campo.equals("discountPercentage")) {
                CommonSteps.getResponse().then().body(campo, equalTo(Float.parseFloat(valorEsperado)));
            } else {
                CommonSteps.getResponse().then().body(campo, equalTo(valorEsperado));
            }
        }

        // (Opcional) Reforça a validação do campo "id"
        CommonSteps.getResponse().then().body("id", notNullValue());
    }
}
