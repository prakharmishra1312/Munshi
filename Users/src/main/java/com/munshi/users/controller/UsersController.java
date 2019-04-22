package com.munshi.users.controller;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.munshi.users.bean.User;

@RestController
@RequestMapping("/api/v1")
public class UsersController {
	@Autowired
	private UserDAOService userService;
	
	@GetMapping("/users/{id}")
	public Resource<User> getUserById(@PathVariable String id){
		User user=userService.getUserById(id);
		ControllerLinkBuilder linkTo= linkTo(methodOn(this.getClass()).getUserById(id));
		Resource<User> resource = new Resource<User>(user);
		resource.add(linkTo.withRel("self"));
		
		return resource;
		
		
	}
	
	@PostMapping("/users")
	public Resource<User> createUser(@RequestBody User user){
		User createdUser=userService.createUser(user);
		ControllerLinkBuilder linkTo= linkTo(methodOn(this.getClass()).getUserById(id));
		Resource<User> resource = new Resource<User>(user);
		resource.add(linkTo.withRel("self"));
		
		return resource;
	}
	
	
}
