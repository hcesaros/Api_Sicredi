package steps;

import io.cucumber.java.pt.*;
import io.restassured.response.Response;

import static org.hamcrest.Matchers.*;

import java.util.Map;

/**
 * Classe de definição de steps para validações relacionadas ao endpoint de usuários.
 * Utiliza RestAssured para verificar dados retornados em requisições GET.
 */
public class GetUsersSteps {

    /**
     * Verifica se a resposta possui uma lista de usuários com pelo menos a quantidade esperada.
     *
     * @param quantidade número mínimo de usuários que a lista deve conter
     */
    @Entao("a resposta deve conter uma lista com pelo menos {int} usuários")
    public void a_resposta_deve_conter_lista_de_usuarios(int quantidade) {
        Response response = CommonSteps.getResponse();
        response.then().body("users", notNullValue());
        response.then().body("users.size()", greaterThanOrEqualTo(quantidade));
    }

    /**
     * Verifica se um campo de coordenada (latitude ou longitude) do usuário está dentro de uma faixa válida.
     * Latitude: -90 a 90 | Longitude: -180 a 180
     *
     * @param campo nome do campo (ex: address.coordinates.lat)
     * @param index índice do usuário na lista
     */
    @Entao("o campo {string} do usuário {int} deve conter uma coordenada válida")
    public void o_campo_do_usuario_deve_conter_uma_coordenada_valida(String campo, int index) {
        Response response = CommonSteps.getResponse();
        Double valor = response.jsonPath().getDouble("users[" + index + "]." + campo);

        if (campo.endsWith(".lat")) {
            assert valor != null && valor >= -90 && valor <= 90
                    : "Latitude inválida: " + valor + " para o campo: " + campo;
        } else if (campo.endsWith(".lng")) {
            assert valor != null && valor >= -180 && valor <= 180
                    : "Longitude inválida: " + valor + " para o campo: " + campo;
        } else {
            throw new IllegalArgumentException("Campo '" + campo + "' não é uma coordenada válida.");
        }
    }

    /**
     * Valida os campos de um usuário específico na resposta, de acordo com um mapa de chave/valor.
     * Realiza conversão automática para números quando aplicável.
     *
     * @param index posição do usuário na lista
     * @param campos mapa com nomes de campos e valores esperados
     */
    @Entao("o usuário na posição {int} deve conter os seguintes dados:")
    public void o_usuario_na_posicao_deve_conter_dados(int index, Map<String, String> campos) {
        Response response = CommonSteps.getResponse();

        for (Map.Entry<String, String> campo : campos.entrySet()) {
            String path = "users[" + index + "]." + campo.getKey();
            String expected = campo.getValue();

            if (expected == null || expected.trim().isEmpty()) {
                response.then().body(path, anyOf(nullValue(), equalTo("")));
            } else if (expected.matches("^-?\\d+$")) {
                response.then().body(path, equalTo(Integer.parseInt(expected)));
            } else if (expected.matches("^-?\\d+\\.\\d+$")) {
                response.then().body(path, equalTo(Float.parseFloat(expected)));
            } else {
                response.then().body(path, equalTo(expected));
            }
        }
    }

    /**
     * Valida se o campo específico de um usuário é igual ao valor string esperado.
     *
     * @param campo nome do campo
     * @param index posição do usuário
     * @param esperado valor esperado em string
     */
    @Entao("o campo {string} do usuário {int} deve ser {string}")
    public void o_campo_do_usuario_deve_ser_string(String campo, int index, String esperado) {
        Response response = CommonSteps.getResponse();
        response.then().body("users[" + index + "]." + campo, equalTo(esperado));
    }

    /**
     * Valida se o campo específico de um usuário é igual ao valor double esperado.
     *
     * @param campo nome do campo
     * @param index posição do usuário
     * @param esperado valor esperado como double
     */
    @Entao("o campo {string} do usuário {int} deve ser {double}")
    public void o_campo_do_usuario_deve_ser_double(String campo, int index, double esperado) {
        Response response = CommonSteps.getResponse();
        response.then().body("users[" + index + "]." + campo, equalTo((float) esperado));
    }

    /**
     * Verifica se a mensagem de erro retornada na resposta contém o texto informado.
     * Útil para validação de erros de negócio ou falhas de autenticação.
     *
     * @param mensagemEsperada texto esperado na mensagem de erro
     */
    @Entao("a mensagem de erro deve conter {string} ou similar")
    public void a_mensagem_de_erro_deve_conter(String mensagemEsperada) {
        Response response = CommonSteps.getResponse();
        String mensagemErro = response.getBody().asString();
        assert mensagemErro.contains(mensagemEsperada) : "Mensagem não encontrada: " + mensagemEsperada;
    }
}
