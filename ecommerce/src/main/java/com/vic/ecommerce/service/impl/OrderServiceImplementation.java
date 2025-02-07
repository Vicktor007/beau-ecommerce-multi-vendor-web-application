package com.vic.ecommerce.service.impl;

import com.vic.ecommerce.domain.OrderStatus;
import com.vic.ecommerce.domain.PaymentStatus;
import com.vic.ecommerce.exception.OrderException;
import com.vic.ecommerce.model.*;
import com.vic.ecommerce.repository.AddressRepository;
import com.vic.ecommerce.repository.OrderItemRepository;
import com.vic.ecommerce.repository.OrderRepository;
import com.vic.ecommerce.repository.UserRepository;
import com.vic.ecommerce.service.CartService;
import com.vic.ecommerce.service.OrderItemService;
import com.vic.ecommerce.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderServiceImplementation implements OrderService {
	
	 @Autowired
 OrderRepository orderRepository;
	 @Autowired
 CartService cartService;
	 @Autowired
 AddressRepository addressRepository;
	 @Autowired
 UserRepository userRepository;
	 @Autowired
 OrderItemService orderItemService;
	 @Autowired
 OrderItemRepository orderItemRepository;
	


	@Override
	public Set<Order> createOrder(User user, Address shippAddress, Cart cart) {
		
//		shippAddress.setUser(user);
		if(!user.getAddresses().contains(shippAddress)){
			user.getAddresses().add(shippAddress);
		}


		Address address= addressRepository.save(shippAddress);



		Map<Long, List<CartItem>> itemsBySeller = cart.getCartItems().stream()
				.collect(Collectors.groupingBy(item -> item.getProduct().getSeller().getId()));

		Set<Order> orders=new HashSet<>();

		for(Map.Entry<Long, List<CartItem>> entry:itemsBySeller.entrySet()){
			Long sellerId=entry.getKey();
			List<CartItem> cartItems=entry.getValue();

			int totalOrderPrice = cartItems.stream()
					.mapToInt(CartItem::getSellingPrice).sum();
			int totalItem=cartItems.stream().mapToInt(CartItem::getQuantity).sum();

			Order createdOrder=new Order();
			createdOrder.setUser(user);
			createdOrder.setSellerId(sellerId);
			createdOrder.setTotalMrpPrice(totalOrderPrice);
			createdOrder.setTotalSellingPrice(totalOrderPrice);
			createdOrder.setTotalItem(totalItem);
			createdOrder.setShippingAddress(address);
			createdOrder.setOrderStatus(OrderStatus.PENDING);
			createdOrder.getPaymentDetails().setStatus(PaymentStatus.PENDING);

			Order savedOrder=orderRepository.save(createdOrder);
			orders.add(savedOrder);


			List<OrderItem> orderItems=new ArrayList<>();

			for(CartItem item: cartItems) {
				OrderItem orderItem=new OrderItem();

				orderItem.setOrder(savedOrder);
				orderItem.setMrpPrice(item.getMrpPrice());
				orderItem.setProduct(item.getProduct());
				orderItem.setQuantity(item.getQuantity());
				orderItem.setSize(item.getSize());
				orderItem.setUserId(item.getUserId());
				orderItem.setSellingPrice(item.getSellingPrice());

				savedOrder.getOrderItems().add(orderItem);

				OrderItem createdOrderItem=orderItemRepository.save(orderItem);

				orderItems.add(createdOrderItem);
			}

		}
		
		return orders;
		
	}

	@Override
	public Order findOrderById(Long orderId) throws OrderException {
		Optional<Order> opt=orderRepository.findById(orderId);
		
		if(opt.isPresent()) {
			return opt.get();
		}
		throw new OrderException("order not exist with id "+orderId);
	}

	@Override
	public List<Order> usersOrderHistory(Long userId) {

		return orderRepository.findByUserId(userId);
	}

	@Override
	public List<Order> getShopsOrders(Long sellerId) {

		return orderRepository.findBySellerIdOrderByOrderDateDesc(sellerId);
	}

	@Override
	public Order updateOrderStatus(Long orderId, OrderStatus orderStatus)
			throws OrderException {
		Order order=findOrderById(orderId);
		order.setOrderStatus(orderStatus);
		return orderRepository.save(order);
	}


	@Override
	public void deleteOrder(Long orderId) throws OrderException {
		Order order = findOrderById(orderId);
		
		orderRepository.deleteById(orderId);
		
	}

	@Override
	public Order cancelOrder(Long orderId, User user) throws OrderException {
		Order order=this.findOrderById(orderId);
		if(user.getId()!=order.getUser().getId()){
			throw new OrderException("you can't perform this action "+orderId);
		}
		order.setOrderStatus(OrderStatus.CANCELLED);

		return orderRepository.save(order);
	}

}
