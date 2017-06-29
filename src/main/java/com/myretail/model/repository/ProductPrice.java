package com.myretail.model.repository;

import org.springframework.data.cassandra.mapping.PrimaryKey;
import org.springframework.data.cassandra.mapping.Table;

/**
 * This class represents the object to map with the relation productprices in
 * Cassandra. It consists of productID, price and currency as data fields.
 * 
 * @author Aruna Sai
 *
 */
@Table(value = "productprices")
public class ProductPrice {
	@PrimaryKey
	private Long productID;
	private Float price;
	private String currency;

	public Long getProductID() {
		return productID;
	}

	public void setProductID(Long productID) {
		this.productID = productID;
	}

	public Float getPrice() {
		return price;
	}

	public void setPrice(Float price) {
		this.price = price;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

}
