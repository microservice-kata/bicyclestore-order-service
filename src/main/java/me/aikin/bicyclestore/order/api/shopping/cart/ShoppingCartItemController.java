package me.aikin.bicyclestore.order.api.shopping.cart;

import lombok.extern.slf4j.Slf4j;
import me.aikin.bicyclestore.order.api.shopping.cart.playload.ShoppingCartItemResponse;
import me.aikin.bicyclestore.order.security.principal.CurrentUser;
import me.aikin.bicyclestore.order.security.principal.UserPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/shopping-cart-items")
public class ShoppingCartItemController {

    @GetMapping
    public List<ShoppingCartItemResponse> getShoppingCartItems(@CurrentUser UserPrincipal currentUser) {
        ShoppingCartItemResponse shoppingCartItemResponse = ShoppingCartItemResponse.builder()
            .username(currentUser.getUsername())
            .build();

        return Collections.singletonList(shoppingCartItemResponse);
    }

}
