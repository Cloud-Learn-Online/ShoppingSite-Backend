package com.anish.ShoppingSite.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Table(name = "users")
public class Users extends AbstractEntity {

	private String user_name;
	
	private String password;
	
	private String email;
	
	@ManyToOne(cascade = CascadeType.MERGE)
	@JoinColumn(name="roles_id")
	private  Roles roles;
	
	@OneToOne(cascade = CascadeType.ALL)
	private ShoppingCart shoppingCart;
	
	@JsonBackReference
	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
	private  List<Order> order;

	public String getUser_name() {
		return user_name;
	}

	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = new BCryptPasswordEncoder().encode(password);
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Roles getRoles() {
		return roles;
	}

	public void setRoles(Roles roles) {
		this.roles = roles;
	}

	public ShoppingCart getShoppingCart() {
		return shoppingCart;
	}

	public void setShoppingCart(ShoppingCart shoppingCart) {
		this.shoppingCart = shoppingCart;
	}

	public List<Order> getOrder() {
		return order;
	}

	public void setOrder(List<Order> order) {
		this.order = order;
	}

	@Override
	public String toString() {
		return "Users [id="+super.getId()+" user_name=" + user_name + ", password=" + password + ", email=" + email + ", roles=" + roles
				+ ", shoppingCart=" + shoppingCart + ", order=" + order + "]";
	}
	

}