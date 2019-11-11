package com.munshi.users.controller;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.munshi.users.bean.User;
import com.munshi.users.service.UserDAOService;

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
	@CrossOrigin(origins = "http://localhost:3000")
	public ResponseEntity<Object> createUser(@RequestBody User user){
		System.out.println("User: "+user.getId()+" ");
		User createdUser=userService.createUser(user);
		
		ControllerLinkBuilder linkTo= linkTo(methodOn(this.getClass()).getUserById(user.getId()));
		
		
		
		return ResponseEntity.created(linkTo.toUri()).build();
	}
	
	@PutMapping("/users/{id}")
	public Resource<User> updateUser(@PathVariable String id, @RequestBody User user){
		User createdUser=userService.updateUser(user);
		ControllerLinkBuilder linkTo= linkTo(methodOn(this.getClass()).getUserById(id));
		Resource<User> resource = new Resource<User>(createdUser);
		resource.add(linkTo.withRel("self"));
		
		return resource;
	}
	@DeleteMapping("/users/{id}")
	public Resource<User> updateUser(@PathVariable String id){
		User createdUser=userService.deleteUser(id);
		ControllerLinkBuilder linkTo= linkTo(methodOn(this.getClass()).getUserById(id));
		Resource<User> resource = new Resource<User>(createdUser);
		resource.add(linkTo.withRel("self"));
		
		return resource;
	}
	
}
