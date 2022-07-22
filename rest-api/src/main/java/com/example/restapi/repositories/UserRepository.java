package com.example.restapi.repositories;

import com.example.restapi.exceptions.EtAuthException;
import com.example.restapi.models.User;

public interface UserRepository {

	Integer create(String firstName, String lastName, String email, String password) throws EtAuthException;
	
	User findByEmailAndPassword(String email, String password) throws EtAuthException;
	
	Integer getCountByEmail(String email);
	
	User findById(Integer userId);
}
