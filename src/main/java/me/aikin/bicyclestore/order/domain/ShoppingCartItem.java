package me.aikin.bicyclestore.order.domain;

import lombok.*;

import java.io.Serializable;
import java.sql.Timestamp;


@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ShoppingCartItem implements Serializable {
    private long id;
    private long userId;
    private long bicycleId;
    private int quantity;
    private Timestamp createdAt;
}
