package com.munshi.users.service;

import com.munshi.users.bean.User;

public interface UserDAOService {

	User getUserById(String id);

	User createUser(User user);

	User updateUser(User user);

	User deleteUser(String id);
	
	

}
