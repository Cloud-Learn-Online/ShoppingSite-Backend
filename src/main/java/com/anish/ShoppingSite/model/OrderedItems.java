package com.anish.ShoppingSite.model;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Table(name="ordered_items")
public class OrderedItems extends AbstractEntity implements Serializable{
	
	private static final long serialVersionUID = 1L;

	@OneToOne
	private Item item;
	
	private int quantity;
	
	private int price;
	
	@Column(columnDefinition = "TEXT default 'REQUEST'")
	private String status;
	
	@JsonBackReference
	@ManyToMany
	@JoinTable(name = "order_ordereditems", joinColumns = { @JoinColumn(name = "ordered_items_id") }, inverseJoinColumns = {
			@JoinColumn(name = "order_id") })
	private Set<Order> order = null;

	public Item getItem() {
		return item;
	}

	public void setItem(Item item) {
		this.item = item;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Set<Order> getOrder() {
		return order;
	}

	public void setOrder(Set<Order> order) {
		this.order = order;
	}

	@Override
	public String toString() {
		return "OrderedItems [id="+super.getId()+" item=" + item + ", quantity=" + quantity + ", price=" + price + ", order=" + order + "]";
	}
	
	
}
