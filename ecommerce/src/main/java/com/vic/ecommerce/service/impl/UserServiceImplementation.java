package com.vic.ecommerce.service.impl;


import com.vic.ecommerce.config.JwtProvider;
import com.vic.ecommerce.exception.UserException;
import com.vic.ecommerce.model.User;
import com.vic.ecommerce.repository.PasswordResetTokenRepository;
import com.vic.ecommerce.repository.UserRepository;
import com.vic.ecommerce.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImplementation implements UserService {


	@Autowired
	 UserRepository userRepository;
	@Autowired
	JwtProvider jwtProvider;
	@Autowired
	PasswordEncoder passwordEncoder;
	@Autowired
	PasswordResetTokenRepository passwordResetTokenRepository;
	@Autowired
	JavaMailSender javaMailSender;
	
	public UserServiceImplementation(
			UserRepository userRepository,
			JwtProvider jwtProvider,
			PasswordEncoder passwordEncoder,
			PasswordResetTokenRepository passwordResetTokenRepository,
			JavaMailSender javaMailSender) {
		
		this.userRepository=userRepository;
		this.jwtProvider=jwtProvider;
		this.passwordEncoder=passwordEncoder;
		this.passwordResetTokenRepository=passwordResetTokenRepository;
		this.javaMailSender=javaMailSender;
		
	}

	@Override
	@Cacheable("userCache")
	public User findUserProfileByJwt(String jwt) throws UserException {
		String email=jwtProvider.getEmailFromJwtToken(jwt);
		
		
		User user = userRepository.findByEmail(email);
		
		if(user==null) {
			throw new UserException("user not exist with email "+email);
		}
		return user;
	}



	
	@Override
	@Cacheable("userCache")
	public User findUserByEmail(String username) throws UserException {
		
		User user=userRepository.findByEmail(username);
		
		if(user!=null) {
			
			return user;
		}
		
		throw new UserException("user not exist with username "+username);
	}



}
