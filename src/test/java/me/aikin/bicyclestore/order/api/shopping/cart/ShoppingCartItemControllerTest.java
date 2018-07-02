package me.aikin.bicyclestore.order.api.shopping.cart;

import me.aikin.bicyclestore.order.api.ApiBaseTest;
import org.junit.jupiter.api.Test;

public class ShoppingCartItemControllerTest extends ApiBaseTest {

    public enum  RoleName {
        ROLE_USER,
        ROLE_ADMIN
    }

    @Test
    public void should_can_get_shopping_cart_items() {
        given().
        when()
            .get("/api/shopping-cart-items")
        .then()
            .statusCode(200);
    }
}
