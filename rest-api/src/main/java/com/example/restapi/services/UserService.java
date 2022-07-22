package com.example.restapi.services;

import com.example.restapi.exceptions.EtAuthException;
import com.example.restapi.models.User;

public interface UserService {

	User validateUser(String email, String password) throws EtAuthException;
	
	User registerUser(String firstName, String lastName, String email, String password) throws EtAuthException;
}
