package com.anish.ShoppingSite.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.springframework.web.bind.annotation.DeleteMapping;

import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name="cart")
public class ShoppingCart extends AbstractEntity implements Serializable{
		
	private static final long serialVersionUID = 1L;
		
	@JsonManagedReference
	@OneToMany(orphanRemoval = true,cascade = CascadeType.ALL)
	private Set<CartItems> cart_items = new HashSet<CartItems>();

	public Set<CartItems> getCart_items() {
		return cart_items;
	}

	public void setCart_items(Set<CartItems> cart_items) {
		this.cart_items = cart_items;
	}

	
	@Override
	public String toString() {
		return "ShoppingCart [id=" +super.getId() + ", cart_items=" + cart_items + "]";
	}
}
