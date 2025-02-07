package com.vic.ecommerce.service;

import com.vic.ecommerce.exception.SellerException;
import com.vic.ecommerce.exception.UserException;
import com.vic.ecommerce.request.LoginRequest;
import com.vic.ecommerce.request.SignupRequest;
import com.vic.ecommerce.response.AuthResponse;
import jakarta.mail.MessagingException;

public interface AuthService {

    void sentLoginOtp(String email) throws UserException, MessagingException;
    String createUser(SignupRequest req) throws SellerException;
    AuthResponse signin(LoginRequest req) throws SellerException;

}
