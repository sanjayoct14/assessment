package com.assessment.test.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.assessment.test.entity.Invoice;
import com.assessment.test.exception.InvoiceNotFoundException;
import com.assessment.test.repository.InvoiceRepository;

@RestController
public class InvoiceController {

	@Autowired
	InvoiceRepository invoiceRepo;

	@GetMapping("/invoices")
	public List<Invoice> viewAllInvoice() {
		return invoiceRepo.findAll();
	}

	@GetMapping("/invoices/{id}")
	public Invoice viewInvoice(@PathVariable Long id) {
		Optional<Invoice> invoice = invoiceRepo.findById(id);
		if (!invoice.isPresent()) {
			throw new InvoiceNotFoundException("id: " + id);
		}
		return invoice.get();
	}

	@PostMapping("/invoices")
	public Invoice addInvoice(@RequestBody Invoice invoice) {
		Invoice in = null;
		try {
			in = invoiceRepo.save(invoice);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return in;
	}
}
