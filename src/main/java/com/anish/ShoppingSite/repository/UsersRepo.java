package com.anish.ShoppingSite.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.anish.ShoppingSite.model.Users;

public interface UsersRepo extends JpaRepository<Users, Long> {
	
	public Users findByEmail(String username);
	
	@Query("from Users")
	public List<Users> findAllUsers();
}
