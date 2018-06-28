package me.aikin.bicyclestore.order.repository;

import me.aikin.bicyclestore.order.domain.ShoppingCartItem;
import org.apache.ibatis.annotations.*;

@Mapper
public interface ShoppingCartItemRepository {
   @Select("SELECT * FROM SHOPPING_CART_ITEM WHERE ID=#{ID}")
   ShoppingCartItem findOne(@Param("ID") Long id);

    @Insert("INSERT into SHOPPING_CART_ITEM(ID, USER_ID, BICYCLE_ID, QUANTITY, CREATED_AT) VALUES(#{id}, #{userId}, #{bicycleId}, #{quantity}, #{createdAt})")
    @Options(useGeneratedKeys=true)
    void save(ShoppingCartItem shoppingCartItem);

}
