package com.max.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.max.entity.Document;

public interface DocumentRepository extends JpaRepository<Document, String>, JpaSpecificationExecutor<Document> {
		
	
}
