package me.aikin.bicyclestore.order.repository;

import me.aikin.bicyclestore.order.domain.ShoppingCartItem;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface ShoppingCartItemRepository {
   @Select("SELECT * FROM SHOPPING_CART_ITEM WHERE ID=#{ID}")
   ShoppingCartItem findOne(Long id);

    @Insert("INSERT into SHOPPING_CART_ITEM(ID, USER_ID, BICYCLE_ID, QUANTITY, CREATED_AT) VALUES(#{id}, #{userId}, #{bicycleId}, #{quantity}, #{createdAt})")
    void save(ShoppingCartItem shoppingCartItem);

}
