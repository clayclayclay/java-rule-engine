package com.max.controller;

import java.lang.reflect.InvocationTargetException;
import java.text.ParseException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.max.entity.DocumentInfo;
import com.max.entity.RuleEntity;
import com.max.enumeration.SourceType;
import com.max.service.RuleEngineServiceDemo2;

@RestController
public class TestController {
	
	
	@Autowired
	private RuleEngineServiceDemo2 ruleEngineServiceDemo2;
	
	@RequestMapping("/")
	public void initTest() {
		ruleEngineServiceDemo2.initRuleEntity();
	}
	
	@RequestMapping("/findAll")
	public List<RuleEntity> findAll() {
		return ruleEngineServiceDemo2.getRuleEntityList();
	}
	
	@RequestMapping("/match")
	public void match() throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException, ParseException {
		DocumentInfo d = new DocumentInfo();
		// source = ERP AND customerName = Max.L AND documentId = 1001 AND docDate = 2016-01-01
		d.setSource(SourceType.ERP);
		d.setCustomerName("Max.L");
		d.setDocumentId("1001");
		d.setDocDate("2016-01-01");
		
		ruleEngineServiceDemo2.runProcess(d);
		
		// source = None AND customerName = Max.H AND documentId = 2000 AND docDate = 2016-05-25
		d.setSource(SourceType.None);
		d.setCustomerName("Max.H");
		d.setDocumentId("2000");
		d.setDocDate("2016-05-25");
		
		ruleEngineServiceDemo2.runProcess(d);
		
	}

}
