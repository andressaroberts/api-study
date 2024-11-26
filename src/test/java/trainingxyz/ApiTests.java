package trainingxyz;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.everyItem;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.notNullValue;
import static org.hamcrest.Matchers.samePropertyValuesAs;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import models.Product;

public class ApiTests {

    @Test
    public void getCategories() {
        String endpoint = "http://localhost:8888/api_testing/category/read.php";
        var response = given().when().get(endpoint).then();
        response.log().body();
    }

    @Test
    public void getProduct() {
        String endpoint = "http://localhost:8888/api_testing/product/read_one.php";

        given().
                queryParam("id", 2).
                when().
                get(endpoint).
                then().
                assertThat().
                statusCode(200).
                body("id", equalTo("2")).
                body("name", equalTo("Cross-Back Training Tank")).
                body("description", equalTo("The most awesome phone of 2013!")).
                body("price", equalTo("299.00")).
                body("category_id", equalTo(2)).
                body("category_name", equalTo("Active Wear - Women"));
    }

    @Test
    public void getComplexResponseForProduct() {
        String endpoint = "http://localhost:8888/api_testing/product/read.php";

        given().
                when().
                get(endpoint).
                then().
                assertThat().
                statusCode(200).
                body("records.size()", equalTo(20)).
                body("records.size()", greaterThan(0)).
                body("records.id", everyItem(notNullValue())).
                body("records.name", everyItem(notNullValue())).
                body("records.price", everyItem(notNullValue())).
                body("records.category_id", everyItem(notNullValue())).
                body("records.category_name", everyItem(notNullValue())).
                body("records.id[0]", equalTo(1003));
    }

    @Test
    public void getHeaders() {
        String endpoint = "http://localhost:8888/api_testing/product/read.php";

        given().
                when().
                get(endpoint).
                then().
                assertThat().
                header("Content-Type", equalTo("application/json; charset=UTF-8"));
    }

    @Test
    public void createProduct() {
        String endpoint = "http://localhost:8888/api_testing/product/create.php";
        String body = """
                {
                "name": "Water Bottle",
                "description": "Blue water bottle. Holds 100ml",
                "price": 5,
                "category_id": 3
                }
                """;
        var response = given().body(body).when().post(endpoint).then();
        response.log().body();
    }

    @Test
    public void updateProduct() {
        String endpoint = "http://localhost:8888/api_testing/product/update.php";
        String body = """
                {
                "id": 1000,
                "name": "Watter Bottle up",
                "description": "Blue water bottle. Holds 100ml",
                "price": 13,
                "category_id": 3
                }
                """;
        var response = given().body(body).when().put(endpoint).then();
        response.log().body();
    }

    @Test
    public void deleteProduct() {
        String endpoint = "http://localhost:8888/api_testing/product/delete.php";
        String body = """
                {
                "id": 1002
                }
                """;
        var response = given().body(body).when().delete(endpoint).then();
        response.log().body();

    }

    @Test
    public void createSerializedProduct() {
        String endpoint = "http://localhost:8888/api_testing/product/create.php";
        Product product = new Product(
                "Sweatband",
                "Challenge about API",
                5,
                3
        );
        var response = given().body(product).when().post(endpoint).then();
        response.log().body();
    }

    @Test
    public void updateSweatBand() {
        String endpoint = "http://localhost:8888/api_testing/product/update.php";
        String body = """
                {
                "id": 1003,
                "price": 8
                }
                """;
        var response = given().body(body).when().put(endpoint).then();
        response.log().body();
    }

    @Test
    public void getDeserializedProduct() {
        String endpoint = "http://localhost:8888/api_testing/product/read_one.php";
        Product expectedProduct = new Product(
                2,
                "Cross-Back Training Tank",
                "The most awesome phone of 2013!",
                299.00,
                2,
                "Active Wear - Women"
        );

        Product actualProduct
                = given().
                        queryParam("id", 2).
                        when().
                        get(endpoint).
                        as(Product.class);

        assertThat(actualProduct, samePropertyValuesAs(expectedProduct));
    }
}
