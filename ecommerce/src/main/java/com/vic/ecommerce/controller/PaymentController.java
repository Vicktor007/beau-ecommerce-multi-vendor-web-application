package com.vic.ecommerce.controller;

import com.vic.ecommerce.domain.PaymentMethod;
import com.vic.ecommerce.model.*;
import com.vic.ecommerce.repository.CartItemRepository;
import com.vic.ecommerce.repository.CartRepository;
import com.vic.ecommerce.response.ApiResponse;
import com.vic.ecommerce.response.PaymentLinkResponse;
import com.vic.ecommerce.service.*;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class PaymentController {

     @Autowired
 UserService userService;
     @Autowired
 PaymentService paymentService;
     @Autowired
 TransactionService transactionService;
     @Autowired
 SellerReportService sellerReportService;
     @Autowired
 SellerService sellerService;
     @Autowired
 CartRepository cartRepository;
     @Autowired
 CartItemRepository cartItemRepository;


    @PostMapping("/api/payment/{paymentMethod}/order/{orderId}")
    public ResponseEntity<PaymentLinkResponse> paymentHandler(
            @PathVariable PaymentMethod paymentMethod,
            @PathVariable Long orderId,
            @RequestHeader("Authorization") String jwt) throws Exception {

        User user = userService.findUserProfileByJwt(jwt);

        PaymentLinkResponse paymentResponse;

        PaymentOrder order= paymentService.getPaymentOrderById(orderId);

//        if(paymentMethod.equals(PaymentMethod.RAZORPAY)){
//            paymentResponse=paymentService.createRazorpayPaymentLink(user,
//                    order.getAmount(),
//                    order.getId());
//        }
//        else{
//            paymentResponse=paymentService.createStripePaymentLink(user,
//                    order.getAmount(),
//                    order.getId());
//        }

        return new ResponseEntity<>(null, HttpStatus.CREATED);
    }


//    @GetMapping("/api/payment/{paymentId}")
//    public ResponseEntity<ApiResponse> paymentSuccessHandler(
//            @PathVariable String paymentId,
//            @RequestParam String paymentLinkId,
//            @RequestHeader("Authorization") String jwt) throws Exception {
//
//        User user = userService.findUserProfileByJwt(jwt);
//
//        PaymentLinkResponse paymentResponse;
//
//        PaymentOrder paymentOrder= paymentService
//                .getPaymentOrderByPaymentId(paymentLinkId);
//
//        boolean paymentSuccess = paymentService.ProceedPaymentOrder(
//                paymentOrder,
//                paymentId,
//                paymentLinkId
//        );
//        if(paymentSuccess){
//            for(Order order:paymentOrder.getOrders()){
//                transactionService.createTransaction(order);
//                Seller seller=sellerService.getSellerById(order.getSellerId());
//                SellerReport report=sellerReportService.getSellerReport(seller);
//                report.setTotalOrders(report.getTotalOrders()+1);
//                report.setTotalEarnings(report.getTotalEarnings()+order.getTotalSellingPrice());
//                report.setTotalSales(report.getTotalSales()+order.getOrderItems().size());
//                sellerReportService.updateSellerReport(report);
//            }
//            Cart cart=cartRepository.findByUserId(user.getId());
//            cart.setCouponPrice(0);
//            cart.setCouponCode(null);
////        Set<CartItem> items=cart.getCartItems();
////        cartItemRepository.deleteAll(items);
////        cart.setCartItems(new HashSet<>());
//            cartRepository.save(cart);
//
//        }
//
//        ApiResponse res = new ApiResponse();
//        res.setMessage("Payment successful");
//        res.setStatus(true);
//
//        return new ResponseEntity<>(res, HttpStatus.CREATED);
//    }
}
