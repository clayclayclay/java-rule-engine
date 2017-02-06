package com.max.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;

import org.springframework.util.CollectionUtils;

import com.max.enumeration.CustomerType;
import com.max.enumeration.DocumentType;
import com.max.enumeration.SourceType;
import com.max.entity.Document;

public class DocumentInfo implements Serializable {

	private static final long serialVersionUID = -5641941619582850523L;

	private String documentId;

	private SourceType source;

	// private String source;

	private DocumentType docType;

	private String comment;

	private String customerId;

	private String customerCode;

	private String customerName;

	private CustomerType customerType;

	private String customerDescription;

	private String fromDate;

	private String toDate;

	private String docDate;

	private BigDecimal totalAmount;
	
//	private String 

	public DocumentInfo() {

	}

	public DocumentInfo(Document document) {
		if (document == null) {
			return;
		}
		documentId = document.getDocumentId();
		source = document.getSource();
		docType = document.getDocType();
		comment = document.getComment();
		customerId = document.getCustomerId();
		customerCode = document.getCustomerCode();
		customerName = document.getCustomerName();
		customerType = document.getCustomerType();
		customerDescription = document.getCustomerDescription();
		fromDate = document.getFromDate();
		toDate = document.getToDate();
		docDate = document.getDocDate();
		totalAmount = document.getTotalAmount();
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

	// public String getSource() {
	// return source;
	// }
	//
	// public void setSource(String source) {
	// this.source = source;
	// }

	public static long getSerialversionuid() {
		return serialVersionUID;
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
