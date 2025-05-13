package steps;

import io.restassured.response.Response;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Classe utilitária para armazenar e reutilizar o estado em testes com Cucumber e RestAssured.
 * Armazena payload, última resposta e histórico de respostas para fins de validação e depuração.
 */
public class CommonSteps {

    // Payload usado em testes com POST ou PUT
    public static String payload;

    // Última resposta recebida
    private static Response response;

    // Histórico de todas as respostas recebidas
    private static final List<Response> history = new ArrayList<>();

    /**
     * Define a resposta atual e armazena no histórico.
     * @param resp resposta da requisição
     */
    public static void setResponse(Response resp) {
        response = resp;
        history.add(resp);
    }

    /**
     * Recupera a última resposta.
     * @return resposta HTTP
     */
    public static Response getResponse() {
        return response;
    }

    /**
     * Retorna o histórico de respostas armazenadas.
     * @return lista de respostas
     */
    public static List<Response> getHistory() {
        return history;
    }

    /**
     * Valida se a resposta existe antes de continuar.
     */
    public static void ensureResponseExists() {
        if (response == null) {
            throw new IllegalStateException("A resposta HTTP ainda não foi definida.");
        }
    }

    /**
     * Valida se o payload foi corretamente preenchido.
     */
    public static void validatePayload() {
        if (payload == null || payload.trim().isEmpty()) {
            throw new IllegalStateException("Payload não definido. Execute o step de definição do payload antes de enviar a requisição.");
        }
    }

    /**
     * Step Cucumber: envia uma requisição GET para o endpoint informado.
     * @param endpoint caminho da API
     */
    @io.cucumber.java.pt.Dado("eu envio uma requisição GET para {string}")
    public void eu_envio_uma_requisicao_get_para(String endpoint) {
        response = given()
                .baseUri("https://dummyjson.com")
                .when()
                .get(endpoint);
    }

    /**
     * Step Cucumber: valida o status da resposta HTTP.
     * @param statusCode código esperado
     */
    @io.cucumber.java.pt.Então("o código de status da resposta deve ser {int}")
    public void o_codigo_de_status_da_resposta_deve_ser(int statusCode) {
        ensureResponseExists();
        response.then().statusCode(statusCode);
    }

    /**
     * Step Cucumber: imprime o corpo da resposta HTTP no console.
     */
    @io.cucumber.java.pt.Então("exibo a resposta no console")
    public void exibo_a_resposta_no_console() {
        ensureResponseExists();
        response.prettyPrint();
    }

    /**
     * Método utilitário para envio de requisição POST com payload JSON.
     * @param endpoint endpoint da API
     * @param payloadJson corpo da requisição
     * @return resposta da API
     */
    public static Response sendPost(String endpoint, String payloadJson) {
        return given()
                .baseUri("https://dummyjson.com")
                .header("Content-Type", "application/json")
                .body(payloadJson)
                .when()
                .post(endpoint);
    }
}
