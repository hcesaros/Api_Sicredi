// Autor: Henrique Cesar
// Data: 06/05/2025

package runners;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

/**
 * Classe responsável por executar os testes BDD com Cucumber integrados ao TestNG.
 * Essa configuração permite rodar os testes definidos em arquivos `.feature`
 * utilizando os steps definidos na camada de automação.
 */
@CucumberOptions(
        features = "src/test/resources/features", // Caminho para os arquivos .feature
        glue = "steps",                           // Pacote onde estão os step definitions
        plugin = {
                "pretty",                             // Saída legível no console
                "html:target/cucumber-report.html"    // Gera um relatório HTML na pasta target
        },
        monochrome = true                         // Remove caracteres especiais do console para melhor leitura
)
public class TestRunner extends AbstractTestNGCucumberTests {
    // A classe herda de AbstractTestNGCucumberTests, que cuida da execução com o TestNG
}