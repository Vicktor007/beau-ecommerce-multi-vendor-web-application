package com.vic.ecommerce.service;

import com.vic.ecommerce.exception.ProductException;
import com.vic.ecommerce.model.Cart;
import com.vic.ecommerce.model.CartItem;
import com.vic.ecommerce.model.Product;
import com.vic.ecommerce.model.User;

public interface CartService {
	
	public CartItem addCartItem(User user,
								Product product,
								String size,
								int quantity) throws ProductException;
	
	public Cart findUserCart(User user);

}
