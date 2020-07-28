package com.anish.ShoppingSite.model;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name = "orders")
public class Order extends AbstractEntity implements Serializable {

	private static final long serialVersionUID = 1L;

	@JsonManagedReference
	@ManyToMany(cascade = CascadeType.ALL)
	@OnDelete(action= OnDeleteAction.CASCADE)
	@JoinTable(name = "order_ordereditems", joinColumns = { @JoinColumn(name = "order_id") }, inverseJoinColumns = {
			@JoinColumn(name = "ordered_items_id") })
	private Set<OrderedItems> order_items;

	private int price;
	
	@Temporal(TemporalType.DATE)
	private Date requested_date = new Date();
	
	@Temporal(TemporalType.TIME)
	private Date requested_time = new Date();

	private String order_status;

	@JsonManagedReference
	@ManyToOne(cascade = CascadeType.MERGE)
	@JoinColumn(name = "user_id")
	private Users user;

	
	public Date getRequested_date() {
		return requested_date;
	}

	public void setRequested_date(Date requested_date) {
		this.requested_date = requested_date;
	}

	public Date getRequested_time() {
		return requested_time;
	}

	public void setRequested_time(Date requested_time) {
		this.requested_time = requested_time;
	}
	
	public Set<OrderedItems> getOrder_items() {
		return order_items;
	}

	public void setOrder_items(Set<OrderedItems> order_items) {
		this.order_items = order_items;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public String getOrder_status() {
		return order_status;
	}

	public void setOrder_status(String order_status) {
		this.order_status = order_status;
	}

	public Users getUser() {
		return user;
	}

	public void setUser(Users user) {
		this.user = user;
	}

	@Override
	public String toString() {
		return "Order [order_items=" + order_items + ", price=" + price + ", order_status=" + order_status + ", user=" + user + "]";
	}	
	
	
}
