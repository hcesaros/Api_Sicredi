package steps;

import io.cucumber.java.pt.*;

import static org.hamcrest.Matchers.*;

/**
 * Classe responsável por validar os campos do corpo da resposta de uma requisição GET,
 * com base nos dados fornecidos em uma tabela de cenários do Cucumber.
 */
public class GetTestSteps {

    /**
     * Verifica se os campos especificados no DataTable existem no corpo da resposta
     * e se seus respectivos valores correspondem ao esperado.
     *
     * Exemplo de tabela Gherkin no cenário:
     *
     * <pre>
     *   E o corpo da resposta deve conter os seguintes campos:
     *     | username | emilys                    |
     *     | id       | 1                         |
     *     | email    | emily.johnson@x.dummyjson.com |
     * </pre>
     *
     * @param dataTable tabela do Cucumber contendo pares campo-valor esperados na resposta
     */
    @E("o corpo da resposta deve conter os seguintes campos:")
    public void oCorpoDaRespostaDeveConterOsSeguintesCampos(io.cucumber.datatable.DataTable dataTable) {
        dataTable.asMap().forEach((key, value) -> {
            CommonSteps.getResponse().then().body(key, equalTo(value));
        });
    }
}
