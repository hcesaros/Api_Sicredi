package steps;

import io.cucumber.java.pt.*;
import io.restassured.path.json.JsonPath;
import org.junit.Assert;

import java.util.List;
import java.util.Map;

public class ProductsSteps {

    @Entao("valido todos os campos do response de produtos")
    public void validoTodosOsCamposDoResponseDeProdutos() {
        JsonPath json = CommonSteps.getResponse().jsonPath();
        List<Map<String, Object>> products = json.getList("products");

        Assert.assertNotNull("Campo 'products' não encontrado no response", products);
        Assert.assertFalse("Lista de produtos está vazia", products.isEmpty());

        for (Map<String, Object> product : products) {
            System.out.println("Validando produto ID: " + product.get("id"));

            Assert.assertNotNull("Campo 'id' está nulo", product.get("id"));
            Assert.assertNotNull("Campo 'title' está nulo", product.get("title"));
            Assert.assertNotNull("Campo 'description' está nulo", product.get("description"));
            Assert.assertNotNull("Campo 'category' está nulo", product.get("category"));
            Assert.assertNotNull("Campo 'price' está nulo", product.get("price"));
            Assert.assertNotNull("Campo 'discountPercentage' está nulo", product.get("discountPercentage"));
            Assert.assertNotNull("Campo 'rating' está nulo", product.get("rating"));
            Assert.assertNotNull("Campo 'stock' está nulo", product.get("stock"));
            Assert.assertNotNull("Campo 'tags' está nulo", product.get("tags"));
            Assert.assertTrue("Campo 'tags' deve ser uma lista", product.get("tags") instanceof List);

            if (!product.containsKey("brand") || product.get("brand") == null) {
                System.out.println("Produto ID " + product.get("id") + " não possui 'brand'");
            } else {
                Assert.assertTrue(product.get("brand") instanceof String);
            }

            Assert.assertNotNull("Campo 'sku' está nulo", product.get("sku"));
            Assert.assertNotNull("Campo 'weight' está nulo", product.get("weight"));

            Map<String, Object> dimensions = (Map<String, Object>) product.get("dimensions");
            Assert.assertNotNull("Campo 'dimensions' está nulo", dimensions);
            Assert.assertNotNull("Campo 'width' em 'dimensions' está nulo", dimensions.get("width"));
            Assert.assertNotNull("Campo 'height' em 'dimensions' está nulo", dimensions.get("height"));
            Assert.assertNotNull("Campo 'depth' em 'dimensions' está nulo", dimensions.get("depth"));

            Assert.assertNotNull("Campo 'warrantyInformation' está nulo", product.get("warrantyInformation"));
            Assert.assertNotNull("Campo 'shippingInformation' está nulo", product.get("shippingInformation"));
            Assert.assertNotNull("Campo 'availabilityStatus' está nulo", product.get("availabilityStatus"));

            List<Map<String, Object>> reviews = (List<Map<String, Object>>) product.get("reviews");
            Assert.assertNotNull("Campo 'reviews' está nulo", reviews);
            for (Map<String, Object> review : reviews) {
                Assert.assertNotNull("Campo 'rating' em 'review' está nulo", review.get("rating"));
                Assert.assertNotNull("Campo 'comment' em 'review' está nulo", review.get("comment"));
                Assert.assertNotNull("Campo 'date' em 'review' está nulo", review.get("date"));
                Assert.assertNotNull("Campo 'reviewerName' em 'review' está nulo", review.get("reviewerName"));
                Assert.assertNotNull("Campo 'reviewerEmail' em 'review' está nulo", review.get("reviewerEmail"));
            }

            Assert.assertNotNull("Campo 'returnPolicy' está nulo", product.get("returnPolicy"));
            Assert.assertNotNull("Campo 'minimumOrderQuantity' está nulo", product.get("minimumOrderQuantity"));

            Map<String, Object> meta = (Map<String, Object>) product.get("meta");
            Assert.assertNotNull("Campo 'meta' está nulo", meta);
            Assert.assertNotNull("Campo 'createdAt' em 'meta' está nulo", meta.get("createdAt"));
            Assert.assertNotNull("Campo 'updatedAt' em 'meta' está nulo", meta.get("updatedAt"));
            Assert.assertNotNull("Campo 'barcode' em 'meta' está nulo", meta.get("barcode"));
            Assert.assertNotNull("Campo 'qrCode' em 'meta' está nulo", meta.get("qrCode"));

            List<String> images = (List<String>) product.get("images");
            Assert.assertNotNull("Campo 'images' está nulo", images);
            Assert.assertFalse("Campo 'images' está vazio", images.isEmpty());

            Assert.assertNotNull("Campo 'thumbnail' está nulo", product.get("thumbnail"));
        }

        Assert.assertNotNull("Campo 'total' está nulo", json.get("total"));
        Assert.assertNotNull("Campo 'skip' está nulo", json.get("skip"));
        Assert.assertNotNull("Campo 'limit' está nulo", json.get("limit"));
    }
}
