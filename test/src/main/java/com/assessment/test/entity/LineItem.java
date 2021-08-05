package com.assessment.test.entity;

import java.math.BigDecimal;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class LineItem {

	@GeneratedValue
	@Id
	private Long id;
	private Long quantity;
	private String description;
	private BigDecimal unitPrice;

	//@ManyToOne(fetch = FetchType.LAZY)
	@ManyToOne(cascade = CascadeType.ALL)
	@JsonIgnore
	Invoice invoice;

	public LineItem() {

	}

	public LineItem(Long id, Long quantity, String description, BigDecimal unitPrice) {
		super();
		this.id = id;
		this.quantity = quantity;
		this.description = description;
		this.unitPrice = unitPrice;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getQuantity() {
		return quantity;
	}

	public void setQuantity(Long quantity) {
		this.quantity = quantity;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public BigDecimal getUnitPrice() {
		return unitPrice;
	}

	public void setUnitPrice(BigDecimal unitPrice) {
		this.unitPrice = unitPrice;
	}

	public Invoice getInvoice() {
		return invoice;
	}

	public void setInvoice(Invoice invoice) {
		this.invoice = invoice;
	}

	public BigDecimal getLineItemTotal() {
		return unitPrice.multiply(BigDecimal.valueOf(quantity));
	}

}
