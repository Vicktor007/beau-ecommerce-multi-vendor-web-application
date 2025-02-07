package com.vic.ecommerce.service;

import com.vic.ecommerce.exception.CartItemException;
import com.vic.ecommerce.exception.UserException;
import com.vic.ecommerce.model.CartItem;


public interface CartItemService {
	
	public CartItem updateCartItem(Long userId, Long id,CartItem cartItem) throws CartItemException, UserException;
	
	public void removeCartItem(Long userId,Long cartItemId) throws CartItemException, UserException;
	
	public CartItem findCartItemById(Long cartItemId) throws CartItemException;
	
}
