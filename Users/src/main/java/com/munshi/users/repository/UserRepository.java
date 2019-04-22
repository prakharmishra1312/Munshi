package com.munshi.users.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.munshi.users.bean.User;

public interface UserRepository extends JpaRepository<User, String> {

}
