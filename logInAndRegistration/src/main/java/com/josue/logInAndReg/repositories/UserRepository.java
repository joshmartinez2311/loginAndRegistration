package com.josue.logInAndReg.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.josue.logInAndReg.models.User;
@Repository
public interface UserRepository extends CrudRepository<User, Long>{
	// will be use for validating a new users email in database
	Optional<User> findByEmail(String email);
	
	//to find user by id
	Optional<User> findById(long id);
	void deleteByid(long id);
	
	// finds all users
	List<User> findAll();
}
