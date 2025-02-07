package com.vic.ecommerce.service;

import com.vic.ecommerce.exception.UserException;
import com.vic.ecommerce.model.User;

public interface UserService {

	public User findUserProfileByJwt(String jwt) throws UserException;
	
	public User findUserByEmail(String email) throws UserException;


}
