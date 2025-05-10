package steps;

import com.google.gson.Gson;
import io.cucumber.java.pt.*;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.junit.Assert;
import utils.TokenManager;

import java.util.HashMap;
import java.util.Map;

import static org.hamcrest.Matchers.*;

import steps.CommonSteps;

/**
 * Classe responsável pelos passos de login utilizando Cucumber e RestAssured.
 * Contém cenários positivos e negativos de autenticação com manipulação de token.
 */
public class LoginSteps {

    private static String payload;

    /**
     * Define o payload de login com username e password recebidos como parâmetros.
     */
    @Dado("que o payload de login possui username {string} e password {string}")
    public void queOPayloadDeLoginPossuiUsernameEPassword(String username, String password) {
        // Cria um mapa com os campos de login
        Map<String, String> loginData = new HashMap<>();
        loginData.put("username", username);
        loginData.put("password", password);

        // Converte para JSON de forma segura
        payload = new Gson().toJson(loginData);
    }

    /**
     * Define o payload como vazio, usado para testes negativos de autenticação.
     */
    @Dado("que o payload de login está vazio")
    public void que_o_payload_esta_vazio() {
        payload = "{}";
    }

    /**
     * Envia uma requisição POST com o payload previamente definido para o endpoint informado.
     */
    @Quando("envio uma requisição POST para {string}")
    public void envioUmaRequisiçãoPOSTPara(String endpoint) {
        RequestSpecification request = RestAssured.given()
                .baseUri("https://dummyjson.com")
                .header("Content-Type", "application/json")
                .body(payload); // <- Corrigido aqui

        Response res = request.post(endpoint);
        CommonSteps.setResponse(res);

        System.out.println("Status: " + CommonSteps.getResponse().statusCode());
        System.out.println("Body:\n" + CommonSteps.getResponse().getBody().asPrettyString());
    }

    /**
     * Envia uma requisição POST para login com os dados de autenticação diretamente no cenário.
     */
    @Quando("envio uma requisição POST para {string} com os dados:")
    public void envio_uma_requisicao_post_para_com_os_dados(String endpoint, Map<String, String> dados) {
        String payload = String.format("{\"username\": \"%s\", \"password\": \"%s\"}",
                dados.get("username"), dados.get("password"));

        Response resp = RestAssured.given()
                .baseUri("https://dummyjson.com")
                .header("Content-Type", "application/json")
                .body(payload)
                .post(endpoint);

        CommonSteps.setResponse(resp);
    }

    /**
     * Verifica se a resposta contém um campo específico com valor não nulo.
     */
    @Entao("a resposta deve conter o campo {string}")
    public void a_resposta_deve_conter_o_campo(String campo) {
        CommonSteps.getResponse().then().body(campo, notNullValue());
    }

    /**
     * Valida se a resposta contém um token JWT válido, não nulo e não vazio.
     * Também armazena o token via TokenManager para uso posterior.
     */
    @Entao("a resposta deve conter um token valido")
    public void a_resposta_deve_conter_um_token_valido() {
        Response response = CommonSteps.getResponse();

        Assert.assertEquals(200, response.getStatusCode());

        String token = response.jsonPath().getString("accessToken");

        Assert.assertNotNull("O token é nulo", token);
        Assert.assertFalse("O token está vazio", token.isEmpty());

        TokenManager.setToken(token);
    }

    /**
     * Compara o valor de um campo da resposta com o valor esperado, tratando números e strings.
     */
    @Entao("o campo {string} na resposta deve ser {string}")
    public void o_campo_na_resposta_deve_ser(String campo, String esperado) {
        if (esperado.matches("^-?\\d+$")) {
            CommonSteps.getResponse().then().body(campo, equalTo(Integer.parseInt(esperado)));
        } else if (esperado.matches("^-?\\d+\\.\\d+$")) {
            CommonSteps.getResponse().then().body(campo, equalTo(Double.parseDouble(esperado)));
        } else {
            CommonSteps.getResponse().then().body(campo, equalTo(esperado));
        }
    }

    /**
     * Envia uma requisição GET para um endpoint sem token de autenticação.
     * Usado para validar retorno 401 Unauthorized.
     */
    @Quando("envio uma requisição GET para {string} sem token")
    public void envioUmaRequisiçãoGETParaSemToken(String endpoint) {
        Response resp = RestAssured.given()
                .baseUri("https://dummyjson.com")
                .get(endpoint);
        CommonSteps.setResponse(resp);
    }

    /**
     * Valida se a resposta JSON possui uma determinada mensagem de erro.
     */
    @E("o corpo da resposta deve conter a mensagem {string}")
    public void oCorpoDaRespostaDeveConterAMensagem(String mensagemEsperada) {
        CommonSteps.getResponse().then().body("message", equalTo(mensagemEsperada));
    }

    /**
     * Envia uma requisição GET com token inválido (sem header Authorization).
     * Ainda assim simula uma tentativa de acesso não autenticado.
     */
    @Quando("envio uma requisição GET para {string} com token inválido")
    public void envioUmaRequisiçãoGETParaComTokenInválido(String endpoint) {
        Response resp = RestAssured.given()
                .baseUri("https://dummyjson.com")
                .get(endpoint);
        CommonSteps.setResponse(resp);
    }

    /**
     * Envia uma requisição GET com um token falso válido em formato JWT, mas que não é autorizado.
     * Usado para simular erro 401 com estrutura de token válida.
     */
    @Quando("envio uma requisição GET para {string} com token proibido")
    public void envioUmaRequisiçãoGETParaComTokenProibido(String endpoint) {
        String fakeToken = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.fake.payload.signature";

        Response resp = RestAssured.given()
                .baseUri("https://dummyjson.com")
                .header("Authorization", "Bearer " + fakeToken)
                .get(endpoint);

        CommonSteps.setResponse(resp);
    }
}
