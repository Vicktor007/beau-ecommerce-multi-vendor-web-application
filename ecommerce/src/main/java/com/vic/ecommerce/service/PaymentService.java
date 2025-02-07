package com.vic.ecommerce.service;


import com.stripe.exception.StripeException;
import com.vic.ecommerce.model.Order;
import com.vic.ecommerce.model.PaymentOrder;
import com.vic.ecommerce.model.User;

import java.util.Set;


public interface PaymentService {

    PaymentOrder createOrder(User user,
                             Set<Order> orders);

    PaymentOrder getPaymentOrderById(Long id) throws Exception;

    PaymentOrder getPaymentOrderByPaymentId(String paymentId) throws Exception;

//    Boolean ProceedPaymentOrder (PaymentOrder paymentOrder,
//                                 String paymentId, String paymentLinkId) ;
//

    String createStripePaymentLink(User user, Long Amount,
                                            Long orderId) throws StripeException;
}
