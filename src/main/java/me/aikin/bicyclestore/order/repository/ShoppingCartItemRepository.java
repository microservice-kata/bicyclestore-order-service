package me.aikin.bicyclestore.order.repository;

import me.aikin.bicyclestore.order.domain.ShoppingCartItem;
import org.apache.ibatis.annotations.*;

// TODO: rename table name

@Mapper
public interface ShoppingCartItemRepository {
    @Results({
        @Result(property = "id", column = "ID"),
        @Result(property = "userId", column = "USER_ID"),
        @Result(property = "bicycleId", column = "BICYCLE_ID"),
        @Result(property = "quantity", column = "QUANTITY"),
        @Result(property = "createdAt", column = "CREATED_AT"),
    })
    @Select("SELECT * FROM SHOPPING_CART_ITEM WHERE ID=#{ID}")
    ShoppingCartItem findOne(@Param("ID") long id);

    @Insert("INSERT into SHOPPING_CART_ITEM(ID, USER_ID, BICYCLE_ID, QUANTITY, CREATED_AT) VALUES(#{id}, #{userId}, #{bicycleId}, #{quantity}, #{createdAt})")
    @Options(useGeneratedKeys = true)
    void save(ShoppingCartItem shoppingCartItem);

}
