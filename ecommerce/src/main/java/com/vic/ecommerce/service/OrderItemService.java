package com.vic.ecommerce.service;


import com.vic.ecommerce.model.OrderItem;

public interface OrderItemService {

	OrderItem getOrderItemById(Long id) throws Exception;
	


}
