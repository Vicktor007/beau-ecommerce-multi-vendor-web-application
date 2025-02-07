package com.vic.ecommerce.repository;

import com.vic.ecommerce.model.Cart;
import com.vic.ecommerce.model.CartItem;
import com.vic.ecommerce.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartItemRepository extends JpaRepository<CartItem, Long> {


    CartItem findByCartAndProductAndSize(Cart cart, Product product, String size);


}
