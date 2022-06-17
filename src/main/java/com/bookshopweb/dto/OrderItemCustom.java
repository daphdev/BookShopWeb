package com.bookshopweb.dto;

public class OrderItemCustom {
	private long id;
	private String createdAt;
	private String name;
	private int status;
	private double total;

	public OrderItemCustom(long id, String createdAt, String name, int status, double total) {
		super();
		this.id = id;
		this.createdAt = createdAt;
		this.name = name;
		this.status = status;
		this.total = total;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(String createdAt) {
		this.createdAt = createdAt;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public double getTotal() {
		return total;
	}

	public void setTotal(double total) {
		this.total = total;
	}

}
