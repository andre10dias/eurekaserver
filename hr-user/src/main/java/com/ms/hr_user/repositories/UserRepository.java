package com.ms.hr_user.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ms.hr_user.entities.User;

public interface UserRepository extends JpaRepository<User, Long> {	
	
	Optional<User> findByEmail(String email);

}
