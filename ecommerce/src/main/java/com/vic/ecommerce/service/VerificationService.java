package com.vic.ecommerce.service;

import com.vic.ecommerce.model.VerificationCode;

public interface VerificationService {

    VerificationCode createVerificationCode(String otp, String email);
}
