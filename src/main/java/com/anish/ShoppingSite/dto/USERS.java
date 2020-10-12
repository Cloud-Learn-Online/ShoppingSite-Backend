package com.anish.ShoppingSite.dto;

import javax.validation.constraints.NotBlank;

public class USERS {

	private String id;
	
	@NotBlank(message = "Name Can not be Empty")
	private String name;
	
	@NotBlank(message = "Email Cannot be Empty")
	private String email;
	
	@NotBlank(message = "Password Can not be Empty")
	private String password;
	
	@NotBlank(message="Default value 'USER' must be entered")
	private String role;
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
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

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}
}
