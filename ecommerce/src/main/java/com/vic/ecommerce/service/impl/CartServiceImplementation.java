package com.vic.ecommerce.service.impl;

import com.vic.ecommerce.exception.ProductException;
import com.vic.ecommerce.model.Cart;
import com.vic.ecommerce.model.CartItem;
import com.vic.ecommerce.model.Product;
import com.vic.ecommerce.model.User;
import com.vic.ecommerce.repository.CartItemRepository;
import com.vic.ecommerce.repository.CartRepository;
import com.vic.ecommerce.service.CartService;
import com.vic.ecommerce.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CartServiceImplementation implements CartService {
	
	 @Autowired
 CartRepository cartRepository;
	 @Autowired
 CartItemRepository cartItemRepository;
	 @Autowired
 ProductService productService;
	

	public Cart findUserCart(User user) {
		Cart cart =	cartRepository.findByUserId(user.getId());

		int totalPrice=0;
		int totalDiscountedPrice=0;
		int totalItem=0;
		for(CartItem cartsItem : cart.getCartItems()) {
			totalPrice+=cartsItem.getMrpPrice();
			totalDiscountedPrice+=cartsItem.getSellingPrice();
			totalItem+=cartsItem.getQuantity();
		}

		cart.setTotalMrpPrice(totalPrice);
		cart.setTotalItem(cart.getCartItems().size());
		cart.setTotalSellingPrice(totalDiscountedPrice-cart.getCouponPrice());
		cart.setDiscount(calculateDiscountPercentage(totalPrice,totalDiscountedPrice));
		cart.setTotalItem(totalItem);
		
		return cartRepository.save(cart);
		
	}

	public static int calculateDiscountPercentage(double mrpPrice, double sellingPrice) {
		if (mrpPrice <= 0) {
			return 0;
		}
		double discount = mrpPrice - sellingPrice;
		double discountPercentage = (discount / mrpPrice) * 100;
		return (int) discountPercentage;
	}

	@Override
	public CartItem addCartItem(User user,
								Product product,
								String size,
								int quantity
								) throws ProductException {
		Cart cart=findUserCart(user);
		
		CartItem isPresent=cartItemRepository.findByCartAndProductAndSize(
				cart, product, size);
		
		if(isPresent == null) {
			CartItem cartItem = new CartItem();
			cartItem.setProduct(product);

			cartItem.setQuantity(quantity);
			cartItem.setUserId(user.getId());
			
			int totalPrice=quantity*product.getSellingPrice();
			cartItem.setSellingPrice(totalPrice);
			cartItem.setMrpPrice(quantity*product.getMrpPrice());
			cartItem.setSize(size);

			cart.getCartItems().add(cartItem);
			cartItem.setCart(cart);

            return cartItemRepository.save(cartItem);
		}

		return isPresent;
	}

}
