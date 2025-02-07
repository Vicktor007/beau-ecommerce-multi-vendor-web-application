package com.vic.ecommerce.service;


import com.vic.ecommerce.exception.WishlistNotFoundException;
import com.vic.ecommerce.model.Product;
import com.vic.ecommerce.model.User;
import com.vic.ecommerce.model.Wishlist;

public interface WishlistService {

    Wishlist createWishlist(User user);

    Wishlist getWishlistByUserId(User user);

    Wishlist addProductToWishlist(User user, Product product) throws WishlistNotFoundException;

}

