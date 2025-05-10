package steps;

import io.cucumber.java.pt.*;
import org.junit.Assert;

import java.util.List;
import java.util.Map;

/**
 * Classe responsável por validar todos os campos esperados ao consultar um produto por ID
 * usando o endpoint GET /products/{id}.
 */
public class ProductByIdSteps {

    /**
     * Step que valida todos os campos do produto retornado no corpo da resposta da API.
     */
    @Entao("o corpo da resposta deve conter todos os campos esperados do produto")
    public void o_corpo_da_resposta_deve_conter_todos_os_campos_esperados_do_produto() {
        var json = CommonSteps.getResponse().jsonPath();

        // 🔍 Campos principais
        Assert.assertEquals(1, json.getInt("id"));
        Assert.assertEquals("Essence Mascara Lash Princess", json.getString("title"));
        Assert.assertNotNull(json.getString("description"));
        Assert.assertEquals("beauty", json.getString("category"));
        Assert.assertEquals(99, json.getInt("stock"));
        Assert.assertEquals("Essence", json.getString("brand"));
        Assert.assertEquals("BEA-ESS-ESS-001", json.getString("sku"));
        Assert.assertEquals(4, json.getInt("weight"));

        // 🔖 Tags do produto
        List<String> tags = json.getList("tags");
        Assert.assertTrue(tags.contains("beauty"));
        Assert.assertTrue(tags.contains("mascara"));

        // 📦 Dimensões
        Map<String, Object> dimensions = json.getMap("dimensions");
        Assert.assertNotNull(dimensions.get("width"));
        Assert.assertNotNull(dimensions.get("height"));
        Assert.assertNotNull(dimensions.get("depth"));

        // 🧾 Informações adicionais
        Assert.assertEquals("1 week warranty", json.getString("warrantyInformation"));
        Assert.assertEquals("Ships in 3-5 business days", json.getString("shippingInformation"));
        Assert.assertEquals("In Stock", json.getString("availabilityStatus"));

        // ⭐ Avaliações (reviews)
        List<Map<String, Object>> reviews = json.getList("reviews");
        for (Map<String, Object> review : reviews) {
            Assert.assertNotNull(review.get("rating"));
            Assert.assertNotNull(review.get("comment"));
            Assert.assertNotNull(review.get("date"));
            Assert.assertNotNull(review.get("reviewerName"));
            Assert.assertNotNull(review.get("reviewerEmail"));
        }

        // 🧬 Metadados
        Map<String, Object> meta = json.getMap("meta");
        Assert.assertNotNull(meta.get("createdAt"));
        Assert.assertNotNull(meta.get("updatedAt"));
        Assert.assertNotNull(meta.get("barcode"));
        Assert.assertNotNull(meta.get("qrCode"));

        // 🖼️ Imagens
        List<String> images = json.getList("images");
        Assert.assertFalse(images.isEmpty());
        Assert.assertNotNull(json.getString("thumbnail"));

        // 📦 Política e quantidade
        Assert.assertEquals("No return policy", json.getString("returnPolicy"));
        Assert.assertEquals(48, json.getInt("minimumOrderQuantity"));
    }
}