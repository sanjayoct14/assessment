package com.assessment.test.entity;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;

@Entity
public class Invoice {

	@Id
	@SequenceGenerator(name="seq", sequenceName = "inv_seq")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="seq")
	private Long id;
	private String client;
	private Long vatRate;
	private Date invoiceDate;

	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "invoice_id")
	private List<LineItem> lineItems;

	public Invoice() {

	}

	public Invoice(Long id, String client, Long vatRate, Date invoiceDate) {
		super();
		this.id = id;
		this.client = client;
		this.vatRate = vatRate;
		this.invoiceDate = invoiceDate;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getClient() {
		return client;
	}

	public void setClient(String client) {
		this.client = client;
	}

	public Long getVatRate() {
		return vatRate;
	}

	public void setVatRate(Long vatRate) {
		this.vatRate = vatRate;
	}

	public Date getInvoiceDate() {
		return invoiceDate;
	}

	public void setInvoiceDate(Date invoiceDate) {
		this.invoiceDate = invoiceDate;
	}

	public List<LineItem> getLineItems() {
		return lineItems;
	}

	public void setLineItems(List<LineItem> lineItems) {
		this.lineItems = lineItems;
	}

	public BigDecimal getSubTotal() {
		BigDecimal subTotal = BigDecimal.ZERO;
		if (lineItems != null) {
			subTotal = lineItems.stream().map(i -> i.getLineItemTotal()).reduce(BigDecimal.ZERO, BigDecimal::add)
					.setScale(2, RoundingMode.HALF_UP);
		}
		return subTotal;
	}

	public BigDecimal getVat() {
		BigDecimal sum = BigDecimal.ZERO;
		if (lineItems != null) {
			sum = lineItems.stream().map(
					i -> i.getLineItemTotal().multiply(BigDecimal.valueOf(vatRate)).divide(BigDecimal.valueOf(100)))
					.reduce(BigDecimal.ZERO, BigDecimal::add).setScale(2, RoundingMode.HALF_UP);
		}
		return sum;
	}

	public BigDecimal getTotal() {
		BigDecimal total = this.getSubTotal().add(this.getVat());
		return total;
	}
}
