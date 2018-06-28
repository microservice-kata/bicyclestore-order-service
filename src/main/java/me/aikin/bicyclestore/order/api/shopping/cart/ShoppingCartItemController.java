package me.aikin.bicyclestore.order.api.shopping.cart;

import lombok.extern.slf4j.Slf4j;
import me.aikin.bicyclestore.order.api.shopping.cart.playload.ShoppingCartItemResponse;
import me.aikin.bicyclestore.order.domain.ShoppingCartItem;
import me.aikin.bicyclestore.order.repository.ShoppingCartItemRepository;
import me.aikin.bicyclestore.order.security.principal.CurrentUser;
import me.aikin.bicyclestore.order.security.principal.UserPrincipal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.Collections;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/shopping-cart-items")
public class ShoppingCartItemController {

    @Autowired
    ShoppingCartItemRepository shoppingCartItemRepository;

    @GetMapping
    public List<ShoppingCartItemResponse> getShoppingCartItems(@CurrentUser UserPrincipal currentUser) {
        ShoppingCartItemResponse shoppingCartItemResponse = ShoppingCartItemResponse.builder()
            .username(currentUser.getUsername())
            .build();

        ShoppingCartItem cartItem = ShoppingCartItem.builder()
            .userId(currentUser.getId())
            .bicycleId(123)
            .quantity(3232)
            .createdAt(Timestamp.from(Instant.now()))
            .build();

        shoppingCartItemRepository.save(cartItem);

        return Collections.singletonList(shoppingCartItemResponse);
    }

}
