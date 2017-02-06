package com.max.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import com.max.enumeration.OperatorType;
import com.max.enumeration.ValueType;

@Entity
@Table(name = "DEMO_RULE_TABLE")
public class RuleEntity {

	@Id
	@GeneratedValue
	private int id;

	@Column(nullable = false)
	private String field1;
	@Column(nullable = false)
	private OperatorType expression1;
	@Column(nullable = false)
	private String value1;
	@Column(nullable = false)
	private ValueType field1Type;

	@Column(nullable = false)
	private String field2;
	@Column(nullable = false)
	private OperatorType expression2;
	@Column(nullable = false)
	private String value2;
	@Column(nullable = false)
	private ValueType field2Type;

	@Column(nullable = false)
	private String field3;
	@Column(nullable = false)
	private OperatorType expression3;
	@Column(nullable = false)
	private String value3;
	@Column(nullable = false)
	private ValueType field3Type;
	
	@Column(nullable = false)
	private String field4;
	@Column(nullable = false)
	private OperatorType expression4;
	@Column(nullable = false)
	private String value4;
	@Column(nullable = false)
	private ValueType field4Type;

	@Column(nullable = false)
	private int ruleCount;

	

	
	@Column(nullable = false)
	private String result;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getField1() {
		return field1;
	}

	public void setField1(String field1) {
		this.field1 = field1;
	}

	public OperatorType getExpression1() {
		return expression1;
	}

	public void setExpression1(OperatorType expression1) {
		this.expression1 = expression1;
	}

	public OperatorType getExpression2() {
		return expression2;
	}

	public void setExpression2(OperatorType expression2) {
		this.expression2 = expression2;
	}

	public String getValue1() {
		return value1;
	}

	public void setValue1(String value1) {
		this.value1 = value1;
	}

	public String getField2() {
		return field2;
	}

	public void setField2(String field2) {
		this.field2 = field2;
	}

	public String getValue2() {
		return value2;
	}

	public void setValue2(String value2) {
		this.value2 = value2;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public ValueType getField1Type() {
		return field1Type;
	}

	public void setField1Type(ValueType field1Type) {
		this.field1Type = field1Type;
	}

	public ValueType getField2Type() {
		return field2Type;
	}

	public void setField2Type(ValueType field2Type) {
		this.field2Type = field2Type;
	}

	public String getField3() {
		return field3;
	}

	public void setField3(String field3) {
		this.field3 = field3;
	}

	public OperatorType getExpression3() {
		return expression3;
	}

	public void setExpression3(OperatorType expression3) {
		this.expression3 = expression3;
	}

	public String getValue3() {
		return value3;
	}

	public void setValue3(String value3) {
		this.value3 = value3;
	}

	public ValueType getField3Type() {
		return field3Type;
	}

	public void setField3Type(ValueType field3Type) {
		this.field3Type = field3Type;
	}

	public int getRuleCount() {
		return ruleCount;
	}

	public void setRuleCount(int ruleCount) {
		this.ruleCount = ruleCount;
	}

	public String getField4() {
		return field4;
	}

	public void setField4(String field4) {
		this.field4 = field4;
	}

	public OperatorType getExpression4() {
		return expression4;
	}

	public void setExpression4(OperatorType expression4) {
		this.expression4 = expression4;
	}

	public String getValue4() {
		return value4;
	}

	public void setValue4(String value4) {
		this.value4 = value4;
	}

	public ValueType getField4Type() {
		return field4Type;
	}

	public void setField4Type(ValueType field4Type) {
		this.field4Type = field4Type;
	}
	

}
