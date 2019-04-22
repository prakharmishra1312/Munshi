package com.munshi.users.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.munshi.users.bean.User;
import com.munshi.users.repository.UserRepository;

@Service
public class UserDAOServiceImpl implements UserDAOService {

	@Autowired
	private UserRepository userRepository;
	
	@Override
	public User getUserById(String id) {
		
		return userRepository.findById(id).get();
	}

	@Override
	public User createUser(User user) {
		
		return userRepository.save(user);
	}

	@Override
	public User updateUser(User user) {
		return userRepository.save(user);
		
	}

	@Override
	public User deleteUser(String id) {
		// TODO Auto-generated method stub
		User user=getUserById(id);
		userRepository.delete(user);
		return user;
	}

}
