package steps;

import io.cucumber.java.pt.*;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import utils.TokenManager;
import static org.hamcrest.Matchers.*;

import java.util.Map;

/**
 * Classe responsável pelos testes de produtos com autenticação via token JWT.
 * Utiliza RestAssured para enviar requisições GET e valida as respostas.
 */
public class AuthProductsSteps {

    /**
     * Envia uma requisição GET autenticada com o token salvo no TokenManager.
     * O token é adicionado no cabeçalho Authorization.
     *
     * @param endpoint Endpoint da API (ex: "/auth/products")
     */
    @Quando("envio uma requisição GET autenticada para {string}")
    public void envio_uma_requisicao_get_autenticada_para(String endpoint) {
        String token = TokenManager.getToken(); // Recupera o token de autenticação
        System.out.println("Usando token: " + token);

        // Envia a requisição GET com o token no cabeçalho
        Response resp = RestAssured.given()
                .baseUri("https://dummyjson.com")
                .header("Authorization", "Bearer " + token)
                .get(endpoint);

        // Armazena a resposta para uso nos próximos steps
        CommonSteps.setResponse(resp);
    }

    /**
     * Verifica se a resposta contém pelo menos a quantidade mínima de produtos.
     *
     * @param qtd Quantidade mínima esperada
     */
    @Entao("a resposta deve conter pelo menos {int} produtos")
    public void a_resposta_deve_conter_pelo_menos_n_produtos(int qtd) {
        CommonSteps.getResponse().then().body("products.size()", greaterThanOrEqualTo(qtd));
    }

    /**
     * Verifica se o produto em uma determinada posição da lista tem o título esperado.
     *
     * @param index  Posição do produto no array
     * @param titulo Título esperado do produto
     */
    @Entao("o produto na posição {int} deve ter título {string}")
    public void o_produto_na_posicao_deve_ter_titulo(int index, String titulo) {
        CommonSteps.getResponse().then().body("products[" + index + "].title", equalTo(titulo));
    }

    /**
     * Valida os campos de um produto específico da lista (por índice).
     * Usa uma tabela Gherkin para comparar campo e valor.
     *
     * @param index     Posição do produto na lista
     * @param dataTable Tabela com os campos e valores esperados
     */
    @Entao("o corpo da resposta deve conter os seguintes dados do produto na posição {int}:")
    public void o_corpo_da_resposta_deve_conter_dados_do_produto(int index, io.cucumber.datatable.DataTable dataTable) {
        Map<String, String> campos = dataTable.asMap();

        for (Map.Entry<String, String> campo : campos.entrySet()) {
            String path = "products[" + index + "]." + campo.getKey(); // Ex: products[0].title
            String esperado = campo.getValue();

            try {
                // Converte valores numéricos (int ou float) para comparação correta
                if (esperado.matches("^-?\\d+$")) {
                    CommonSteps.getResponse().then().body(path, equalTo(Integer.parseInt(esperado)));
                } else if (esperado.matches("^-?\\d+\\.\\d+$")) {
                    CommonSteps.getResponse().then().body(path, equalTo(Float.parseFloat(esperado)));
                } else {
                    CommonSteps.getResponse().then().body(path, equalTo(esperado));
                }
            } catch (NumberFormatException e) {
                // Se falhar na conversão, compara como string
                CommonSteps.getResponse().then().body(path, equalTo(esperado));
            }
        }
    }

    /**
     * Valida campos de nível global da resposta JSON (fora do array de produtos).
     * Ex: total, limit, skip
     *
     * @param dataTable Tabela com os campos globais e seus valores esperados
     */
    @Entao("o corpo da resposta deve conter os campos globais:")
    public void o_corpo_da_resposta_deve_conter_os_campos_globais(io.cucumber.datatable.DataTable dataTable) {
        Map<String, String> globais = dataTable.asMap();

        for (Map.Entry<String, String> campo : globais.entrySet()) {
            String path = campo.getKey(); // Ex: total
            String esperado = campo.getValue();

            try {
                if (esperado.matches("^-?\\d+$")) {
                    CommonSteps.getResponse().then().body(path, equalTo(Integer.parseInt(esperado)));
                } else if (esperado.matches("^-?\\d+\\.\\d+$")) {
                    CommonSteps.getResponse().then().body(path, equalTo(Float.parseFloat(esperado)));
                } else {
                    CommonSteps.getResponse().then().body(path, equalTo(esperado));
                }
            } catch (NumberFormatException e) {
                CommonSteps.getResponse().then().body(path, equalTo(esperado));
            }
        }
    }
}
