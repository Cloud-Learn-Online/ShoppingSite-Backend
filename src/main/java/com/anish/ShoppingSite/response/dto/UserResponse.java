package com.anish.ShoppingSite.response.dto;

import com.anish.ShoppingSite.model.Users;

public class UserResponse {
	private String name;
	private String email;
	private String role;
	
	public UserResponse(Users usr) {
		this.name = usr.getUserName();
		this.email = usr.getEmail();
		this.role = usr.getRoles().getRole().split("_")[1]	;
	}
	
	public UserResponse() {
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

}
