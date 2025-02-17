package com.vic.ecommerce.repository;

import com.vic.ecommerce.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

	
	public User findByEmail(String username);

}
