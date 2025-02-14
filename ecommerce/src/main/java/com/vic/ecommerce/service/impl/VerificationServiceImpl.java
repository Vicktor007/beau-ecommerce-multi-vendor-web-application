package com.vic.ecommerce.service.impl;

import com.vic.ecommerce.model.VerificationCode;
import com.vic.ecommerce.repository.VerificationCodeRepository;
import com.vic.ecommerce.service.VerificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class VerificationServiceImpl implements VerificationService {

     @Autowired
 VerificationCodeRepository verificationCodeRepository;

    VerificationServiceImpl(VerificationCodeRepository verificationCodeRepository){

        this.verificationCodeRepository = verificationCodeRepository;
    }

    @Override
    public VerificationCode createVerificationCode(String otp,String email) {
        VerificationCode isExist=verificationCodeRepository.findByEmail(email);

        if(isExist!=null) {
            verificationCodeRepository.delete(isExist);
        }

        VerificationCode verificationCode=new VerificationCode();
        verificationCode.setOtp(otp);
        verificationCode.setEmail(email);
        verificationCode.setExpiryDate(LocalDateTime.now().plusMinutes(1)); // Sets expiry date to 1 minute from now

        return verificationCodeRepository.save(verificationCode);

    }

    @Override
    public boolean isOtpExpired(String email) {
        VerificationCode verificationCode = verificationCodeRepository.findByEmail(email);
        if (verificationCode == null) {
            return true; // No OTP found, considered expired
        }
        return LocalDateTime.now().isAfter(verificationCode.getExpiryDate());
    }
}
