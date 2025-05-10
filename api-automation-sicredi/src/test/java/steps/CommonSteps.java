package steps;

import io.restassured.response.Response;

import static io.restassured.RestAssured.*;

/**
 * Classe utilitária usada para armazenar e reutilizar a última resposta HTTP
 * em testes com Cucumber e RestAssured.
 * Também contém steps genéricos de requisição e validação.
 */
public class CommonSteps {

    // Armazena o corpo da requisição (payload), usado em testes com POST ou PUT
    public static String payload;

    // Armazena a última resposta recebida de uma requisição HTTP
    private static Response response;

    /**
     * Salva a resposta HTTP atual para que outros steps possam acessá-la.
     *
     * @param resp A resposta da requisição executada
     */
    public static void setResponse(Response resp) {
        response = resp;
    }

    /**
     * Recupera a última resposta armazenada.
     *
     * @return A resposta HTTP da última requisição
     */
    public static Response getResponse() {
        return response;
    }

    /**
     * Step Cucumber que envia uma requisição GET para o endpoint informado
     * e armazena a resposta para validação posterior.
     *
     * @param endpoint Caminho da API, como "/users" ou "/products"
     */
    @io.cucumber.java.pt.Dado("eu envio uma requisição GET para {string}")
    public void eu_envio_uma_requisicao_get_para(String endpoint) {
        response = given()
                .baseUri("https://dummyjson.com")
                .when()
                .get(endpoint);
    }

    /**
     * Step Cucumber que valida o status da resposta HTTP.
     * Exemplo: 200 para sucesso, 404 para não encontrado.
     *
     * @param statusCode Código esperado da resposta
     */
    @io.cucumber.java.pt.Então("o código de status da resposta deve ser {int}")
    public void o_codigo_de_status_da_resposta_deve_ser(int statusCode) {
        getResponse().then().statusCode(statusCode);
    }

    /**
     * Step Cucumber que imprime no console o corpo da resposta HTTP.
     * Muito útil para depuração e análise do retorno da API.
     */
    @io.cucumber.java.pt.Então("exibo a resposta no console")
    public void exibo_a_resposta_no_console() {
        getResponse().prettyPrint();
    }
}
