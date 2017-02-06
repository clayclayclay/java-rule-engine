package com.max.entity;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.Table;

import com.max.enumeration.CustomerType;
import com.max.enumeration.DocumentType;
import com.max.enumeration.SourceType;

@Entity
@Table(name = "DEMO_DOCUMENT")
public class Document {
	private static final long serialVersionUID = -1996411838055767133L;

	@Id
	private String documentId;

	@Column(nullable = false)
	private SourceType source;

	@Column(nullable = false)
	@Enumerated(EnumType.STRING)
	private DocumentType docType;

	@Column
	private String comment;

	@Column(nullable = false)
	private String customerId;

	@Column
	private String customerCode;

	@Column
	private String customerName;

	@Column
	@Enumerated(EnumType.STRING)
	private CustomerType customerType;

	@Column
	private String customerDescription;

	@Column
	private String fromDate;

	@Column
	private String toDate;

	@Column
	private String docDate;

	@Column
	private BigDecimal totalAmount;

	public Document() {

	}

	public String getDocumentId() {
		return documentId;
	}

	public void setDocumentId(String documentId) {
		this.documentId = documentId;
	}

	public SourceType getSource() {
		return source;
	}

	public void setSource(SourceType source) {
		this.source = source;
	}

	public DocumentType getDocType() {
		return docType;
	}

	public void setDocType(DocumentType docType) {
		this.docType = docType;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public String getCustomerId() {
		return customerId;
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}

	public String getCustomerCode() {
		return customerCode;
	}

	public void setCustomerCode(String customerCode) {
		this.customerCode = customerCode;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public CustomerType getCustomerType() {
		return customerType;
	}

	public void setCustomerType(CustomerType customerType) {
		this.customerType = customerType;
	}

	public String getCustomerDescription() {
		return customerDescription;
	}

	public void setCustomerDescription(String customerDescription) {
		this.customerDescription = customerDescription;
	}

	public String getFromDate() {
		return fromDate;
	}

	public void setFromDate(String fromDate) {
		this.fromDate = fromDate;
	}

	public String getToDate() {
		return toDate;
	}

	public void setToDate(String toDate) {
		this.toDate = toDate;
	}

	public String getDocDate() {
		return docDate;
	}

	public void setDocDate(String docDate) {
		this.docDate = docDate;
	}

	public BigDecimal getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(BigDecimal totalAmount) {
		this.totalAmount = totalAmount;
	}
}
