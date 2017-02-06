package com.max.service;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.max.dao.RuleEntityRepository;
import com.max.entity.DocumentInfo;
import com.max.entity.RuleEntity;
import com.max.enumeration.OperatorType;
import com.max.enumeration.SourceType;
import com.max.enumeration.ValueType;

@Service
public class RuleEngineServiceDemo2 {

	private RuleEntity ruleEntity;

	@Autowired
	private RuleEntityRepository ruleEntityRepository;

	public void initRuleEntity() {
		// 初始化三条rule
		// 第一条rule : Source = ERP and CustomerName = Max.L and DocumentId = 1001 and DocDate = 2016-01-01
		ruleEntity = new RuleEntity();

		ruleEntity.setField1("Source");
		ruleEntity.setExpression1(OperatorType.EQUAL);
		ruleEntity.setValue1("ERP");
		ruleEntity.setField1Type(ValueType.ENUM);

		ruleEntity.setField2("CustomerName");
		ruleEntity.setExpression2(OperatorType.EQUAL);
		ruleEntity.setValue2("Max.L");
		ruleEntity.setField2Type(ValueType.STRING);

		ruleEntity.setField3("DocumentId");
		ruleEntity.setExpression3(OperatorType.EQUAL);
		ruleEntity.setValue3("1001");
		ruleEntity.setField3Type(ValueType.INT);

		ruleEntity.setField4("DocDate");
		ruleEntity.setExpression4(OperatorType.EQUAL);
		ruleEntity.setValue4("2016-01-01");
		ruleEntity.setField4Type(ValueType.DATE);

		ruleEntity.setRuleCount(4);
		ruleEntity.setResult("result1");

		// 持久化保存
		ruleEntityRepository.save(ruleEntity);

		// 第二条rule: Source != ERP and CustomerName != Max.L and DocumentId != 1005 and DocDate != 2016-10-19
		ruleEntity = new RuleEntity();

		ruleEntity.setField1("Source");
		ruleEntity.setExpression1(OperatorType.NO_EQUAL);
		ruleEntity.setValue1("IBM");
		ruleEntity.setField1Type(ValueType.ENUM);

		ruleEntity.setField2("CustomerName");
		ruleEntity.setExpression2(OperatorType.NO_EQUAL);
		ruleEntity.setValue2("Max.L");
		ruleEntity.setField2Type(ValueType.STRING);

		ruleEntity.setField3("DocumentId");
		ruleEntity.setExpression3(OperatorType.NO_EQUAL);
		ruleEntity.setValue3("1005");
		ruleEntity.setField3Type(ValueType.INT);

		ruleEntity.setField4("DocDate");
		ruleEntity.setExpression4(OperatorType.NO_EQUAL);
		ruleEntity.setValue4("2016-10-19");
		ruleEntity.setField4Type(ValueType.DATE);

		ruleEntity.setRuleCount(4);
		ruleEntity.setResult("result2");

		// 持久化保存
		ruleEntityRepository.save(ruleEntity);

		// 第三条rule: Source = APPLE and CustomerName = Max.H and DocumentId >1200 and DocDate = 2016-10-20
		ruleEntity = new RuleEntity();

		ruleEntity.setField1("Source");
		ruleEntity.setExpression1(OperatorType.EQUAL);
		ruleEntity.setValue1("APPLE");
		ruleEntity.setField1Type(ValueType.ENUM);

		ruleEntity.setField2("CustomerName");
		ruleEntity.setExpression2(OperatorType.EQUAL);
		ruleEntity.setValue2("Max.H");
		ruleEntity.setField2Type(ValueType.STRING);

		ruleEntity.setField3("DocumentId");
		ruleEntity.setExpression3(OperatorType.BIGGER);
		ruleEntity.setValue3("1200");
		ruleEntity.setField3Type(ValueType.INT);

		ruleEntity.setField4("DocDate");
		ruleEntity.setExpression4(OperatorType.EQUAL);
		ruleEntity.setValue4("2016-10-20");
		ruleEntity.setField4Type(ValueType.DATE);

		ruleEntity.setRuleCount(4);
		ruleEntity.setResult("result3");
		// 持久化保存
		ruleEntityRepository.save(ruleEntity);
		System.out.println("init done!");
	}

	// 获取所有ruleEntity
	public List<RuleEntity> getRuleEntityList() {
		return ruleEntityRepository.findAll();
	}

	/*
	 * 进行匹配 
	 * 第一条rule : Source = ERP and CustomerName = Max.L and DocumentId = 1001 and DocDate EXISTS_IN 2016-01-01, 2016-01-20 ---> result = result1
	 * 第二条rule: Source != ERP and CustomerName != Max.L and DocumentId != 1005 and DocDate BETWEEN 2016-01-01 AND 2016-01-20 ---> result = result2
	 * 第三条rule: Source = APPLE and CustomerName = Max.H and DocumentId >1200 and DocDate = 2016-10-20 ---> result = result3 
	 * 
	 * 测试数据：
	 * DocumentInfo1 : source = ERP AND customerName = Max.L AND documentId = 1001 AND docDate = 2016-01-01 ---> result = result1
	 * DocumentInfo2 : source = None AND customerName = Max.H AND documentId = 2000 AND docDate = 2016-05-25 ---> result = result2
	 */
	public void runProcess(DocumentInfo documentInfo) throws IllegalAccessException, IllegalArgumentException,
			InvocationTargetException, NoSuchMethodException, SecurityException, ParseException {
		StringBuffer result = new StringBuffer();
		List<RuleEntity> ruleEntityList = getRuleEntityList();
		boolean isMatch = false;
		for (RuleEntity ruleEntity : ruleEntityList) {
			isMatch = matchRule(documentInfo, ruleEntity);
			if (isMatch) {
				result.append(ruleEntity.getResult() + ",");
			} else {
				continue;
			}
		}
		System.out.println("the documentInfo mapping result is :" + (result.toString().length() == 0? "nothing result" : result.toString()));
	}

	// 进行每条规则的匹配
	public boolean matchRule(DocumentInfo documentInfo, RuleEntity ruleEntity)
			throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException,
			SecurityException, ParseException {

		boolean isRuled = false;
		Map<String, Method> methodMap = new HashMap<String, Method>();

		// 分别取每条规则中各个表达式进行匹配
		for (int i = 1; i <= ruleEntity.getRuleCount(); i++) {

			String getExpressionMethodName = "getExpression" + i;
			Method getExpression = ruleEntity.getClass().getMethod(getExpressionMethodName);
			methodMap.put("expression", getExpression);

			String getFieldMethodName = "getField" + i;
			Method getField = ruleEntity.getClass().getMethod(getFieldMethodName);
			methodMap.put("field", getField);

			String getValueMethodName = "getValue" + i;
			Method getValue = ruleEntity.getClass().getMethod(getValueMethodName);
			methodMap.put("value", getValue);

			String getValueTypeMethodName = "getField" + i + "Type";
			Method getValueType = ruleEntity.getClass().getMethod(getValueTypeMethodName);
			methodMap.put("valueType", getValueType);

			String getKeyFieldMethodName = "get"
					+ (String) ruleEntity.getClass().getMethod(getFieldMethodName).invoke(ruleEntity,  (Object[]) null);
			Method getKeyField = documentInfo.getClass().getMethod(getKeyFieldMethodName);
			methodMap.put("keyField", getKeyField);

			ValueType valueType = (ValueType) getValueType.invoke(ruleEntity, (Object[]) null);

			switch (valueType) {
			case INT:
				isRuled = processByInt(ruleEntity, documentInfo, methodMap);
				break;
			case STRING:
				isRuled = processByString(ruleEntity, documentInfo, methodMap);
				break;
			case ENUM:
				isRuled = processByEnum(ruleEntity, documentInfo, methodMap);
				break;
			case DATE:
				isRuled = processByDate(ruleEntity, documentInfo, methodMap);
			}
			if (!isRuled) {
				return false;
			}
		}

		System.out.println("the output result is :" + ruleEntity.getResult());
		return true;
	}

	// 处理 int 类型的匹配
	public boolean processByInt(RuleEntity rule, DocumentInfo documentInfo, Map<String, Method> map)
			throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		int valueInt, keyInt;
		int leftValue, rightValue;
		String valueCollect, valueRange;
		String[] valueCollectSplit;
		OperatorType operatorType = (OperatorType) map.get("expression").invoke(rule, (Object[]) null);
		switch (operatorType) {

		case EQUAL:
			valueInt = Integer.valueOf((String) map.get("value").invoke(rule, (Object[]) null));
			keyInt = Integer.valueOf((String) map.get("keyField").invoke(documentInfo, (Object[]) null));
			return keyInt == valueInt ? true : false;

		case NO_EQUAL:
			valueInt = Integer.valueOf((String) map.get("value").invoke(rule, (Object[]) null));
			keyInt = Integer.valueOf((String) map.get("keyField").invoke(documentInfo, (Object[]) null));
			return keyInt != valueInt ? true : false;

		case BIGGER:
			valueInt = Integer.valueOf((String) map.get("value").invoke(rule, (Object[]) null));
			keyInt = Integer.valueOf((String) map.get("keyField").invoke(documentInfo, (Object[]) null));
			return keyInt > valueInt ? true : false;

		case SMALLER:
			valueInt = Integer.valueOf((String) map.get("value").invoke(rule, (Object[]) null));
			keyInt = Integer.valueOf((String) map.get("keyField").invoke(documentInfo, (Object[]) null));
			return keyInt < valueInt ? true : false;

		case EQUAL_BIGGER:
			valueInt = Integer.valueOf((String) map.get("value").invoke(rule, (Object[]) null));
			keyInt = Integer.valueOf((String) map.get("keyField").invoke(documentInfo, (Object[]) null));
			return keyInt >= valueInt ? true : false;

		case EQUAL_SMALLER:
			valueInt = Integer.valueOf((String) map.get("value").invoke(rule, (Object[]) null));
			keyInt = Integer.valueOf((String) map.get("keyField").invoke(documentInfo, (Object[]) null));
			return keyInt <= valueInt ? true : false;

		case EXISTS_IN:
			// 假设规则中的value值为一个集合，该集合的格式为： 23,45,12,78,233
			valueCollect = (String) map.get("value").invoke(rule, (Object[]) null);
			keyInt = Integer.valueOf((String) map.get("keyField").invoke(documentInfo, (Object[]) null));
			valueCollectSplit = valueCollect.split(",");
			for (String value : valueCollectSplit) {
				if (Integer.valueOf(value) == keyInt) {
					return true;
				}
			}
			return false;

		case NO_EXISTS_IN:
			// 假设规则中的value值为一个集合，该集合的格式为： 23,45,12,78,233
			valueCollect = (String) map.get("value").invoke(rule, (Object[]) null);
			keyInt = Integer.valueOf((String) map.get("keyField").invoke(documentInfo, (Object[]) null));
			valueCollectSplit = valueCollect.split(",");
			for (String value : valueCollectSplit) {
				if (!(Integer.valueOf(value) == keyInt)) {
					return true;
				}
			}
			return false;

		case BETWEEN:
			// 假设规则中的value值为一个区间，该集合的格式为： 10,100
			valueRange = (String) map.get("value").invoke(rule, (Object[]) null);
			keyInt = Integer.valueOf((String) map.get("keyField").invoke(documentInfo, (Object[]) null));
			valueCollectSplit = valueRange.split(",");
			leftValue = Integer.valueOf(valueCollectSplit[0]);
			rightValue = Integer.valueOf(valueCollectSplit[1]);
			if (leftValue <= keyInt && keyInt <= rightValue) {
				return true;
			}
			return false;

		case NO_BETWEEN:
			// 假设规则中的value值为一个区间，该集合的格式为： 10,100
			valueRange = (String) map.get("value").invoke(rule, (Object[]) null);
			keyInt = Integer.valueOf((String) map.get("keyField").invoke(documentInfo, (Object[]) null));
			valueCollectSplit = valueRange.split(",");
			leftValue = Integer.valueOf(valueCollectSplit[0]);
			rightValue = Integer.valueOf(valueCollectSplit[1]);
			if (leftValue > keyInt || keyInt > rightValue) {
				return true;
			}
			return false;

//		case LIKE:
//
//			break;
//		case NO_LIKE:
//
//			break;
		default:
			return false;
		}
	}

	// 处理 String类型的匹配
	public boolean processByString(RuleEntity rule, DocumentInfo documentInfo, Map<String, Method> map)
			throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		String valueString, keyString, valueCollect;
		String[] valueCollectSplit;
		OperatorType operatorType = (OperatorType) map.get("expression").invoke(rule, (Object[]) null);
		switch (operatorType) {
		case EQUAL:
			valueString = (String) map.get("value").invoke(rule, (Object[]) null);
			keyString = (String) map.get("keyField").invoke(documentInfo, (Object[]) null);
			return valueString.equals(keyString) ? true : false;
			
		case NO_EQUAL:
			valueString = (String) map.get("value").invoke(rule, (Object[]) null);
			keyString = (String) map.get("keyField").invoke(documentInfo, (Object[]) null);
			return valueString.equals(keyString) ? false : true;
			
		case EXISTS_IN:
			// 假设规则中的value值为一个集合，该集合的格式为： ab, cd, ef, gh
			valueCollect = (String) map.get("value").invoke(rule, (Object[]) null);
			keyString = (String) map.get("keyField").invoke(documentInfo, (Object[]) null);
			valueCollectSplit = valueCollect.split(",");
			for (String value : valueCollectSplit) {
				if (value.equals(keyString)) {
					return true;
				}
			}
			return false;

		case NO_EXISTS_IN:
			// 假设规则中的value值为一个集合，该集合的格式为： ab,cd,ef,gh
			valueCollect = (String) map.get("value").invoke(rule, (Object[]) null);
			keyString = (String) map.get("keyField").invoke(documentInfo, (Object[]) null);
			valueCollectSplit = valueCollect.split(",");
			for (String value : valueCollectSplit) {
				if (value.equals(keyString)) {
					return false;
				}
			}
			return true;
			
//		case BETWEEN:
//			break;
//		case NO_BETWEEN:
//			break;
			
//		case LIKE:
//			break;
//		case NO_LIKE:
//			break;
		default:
			return false;
		}
	}

	// 处理 Enum 类型的匹配
	@SuppressWarnings("rawtypes")
	public boolean processByEnum(RuleEntity rule, DocumentInfo documentInfo, Map<String, Method> map)
			throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		String valueString, valueCollect;
		String[] valueCollectSplit;
		Enum keyString;
		OperatorType operatorType = (OperatorType) map.get("expression").invoke(rule, (Object[]) null);
		switch (operatorType) {
		case EQUAL:
			valueString = (String) map.get("value").invoke(rule, (Object[]) null);
			keyString = (Enum) map.get("keyField").invoke(documentInfo, (Object[]) null);
			return valueString.equals(keyString.toString()) ? true : false;
			
		case NO_EQUAL:
			valueString = (String) map.get("value").invoke(rule, (Object[]) null);
			keyString = (Enum) map.get("keyField").invoke(documentInfo, (Object[]) null);
			return valueString.equals(keyString.toString()) ? false : true;
			
		case EXISTS_IN:
			valueCollect = (String) map.get("value").invoke(rule, (Object[]) null);
			keyString = (Enum) map.get("keyField").invoke(documentInfo, (Object[]) null);
			valueCollectSplit = valueCollect.split(",");
			for (String value : valueCollectSplit) {
				if (value.equals(keyString.toString())) {
					return true;
				}
			}
			return false;
			
		case NO_EXISTS_IN:
			valueCollect = (String) map.get("value").invoke(rule, (Object[]) null);
			keyString = (Enum) map.get("keyField").invoke(documentInfo, (Object[]) null);
			valueCollectSplit = valueCollect.split(",");
			for (String value : valueCollectSplit) {
				if (value.equals(keyString.toString())) {
					return false;
				}
			}
			return true;
			
//		case BETWEEN:
//			break;
//		case NO_BETWEEN:
//			break;
//		case LIKE:
//			break;
//		case NO_LIKE:
//			break;
		default:
			return false;
		}
	}

	// 处理 Date 类型的匹配
	public boolean processByDate(RuleEntity rule, DocumentInfo documentInfo, Map<String, Method> map)
			throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, ParseException {
		String valueString, valueCollect, keyString;
		String[] valueCollectSplit;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		long valueLong, keyLong;
		OperatorType operatorType = (OperatorType) map.get("expression").invoke(rule, (Object[]) null);
		switch (operatorType) {
		case EQUAL:
			valueString = (String) map.get("value").invoke(rule, (Object[]) null);
			valueLong = sdf.parse(valueString).getTime();
			keyString = (String) map.get("keyField").invoke(documentInfo, (Object[]) null);
			keyLong = sdf.parse(keyString).getTime();
			return valueLong == keyLong ? true : false;
			
		case NO_EQUAL:
			valueString = (String) map.get("value").invoke(rule, (Object[]) null);
			valueLong = sdf.parse(valueString).getTime();
			keyString = (String) map.get("keyField").invoke(documentInfo, (Object[]) null);
			keyLong = sdf.parse(keyString).getTime();
			return valueLong != keyLong ? true : false;
			
		case EXISTS_IN:
			valueString = (String) map.get("value").invoke(rule, (Object[]) null);
			keyString = (String) map.get("keyField").invoke(documentInfo, (Object[]) null);
			keyLong = sdf.parse(keyString).getTime();
			valueCollectSplit = valueString.split(",");
			for (String value : valueCollectSplit) {
				valueLong = sdf.parse(value).getTime();
				if (valueLong == keyLong) {
					return true;
				}
			}
			return false;
			
		case NO_EXISTS_IN:
			valueString = (String) map.get("value").invoke(rule, (Object[]) null);
			keyString = (String) map.get("keyField").invoke(documentInfo, (Object[]) null);
			keyLong = sdf.parse(keyString).getTime();
			valueCollectSplit = valueString.split(",");
			for (String value : valueCollectSplit) {
				valueLong = sdf.parse(value).getTime();
				if (valueLong != keyLong) {
					return true;
				}
			}
			return false;
			
		case BETWEEN:
			valueString = (String) map.get("value").invoke(rule, (Object[]) null);
			keyString = (String) map.get("keyField").invoke(documentInfo, (Object[]) null);
			keyLong = sdf.parse(keyString).getTime();
			valueCollectSplit = valueString.split(",");
			if (keyLong >= sdf.parse(valueCollectSplit[0]).getTime() && keyLong <= sdf.parse(valueCollectSplit[1]).getTime()) {
				return true;
			}
			return false;
			
		case NO_BETWEEN:
			valueString = (String) map.get("value").invoke(rule, (Object[]) null);
			keyString = (String) map.get("keyField").invoke(documentInfo, (Object[]) null);
			keyLong = sdf.parse(keyString).getTime();
			valueCollectSplit = valueString.split(",");
			if (keyLong >= sdf.parse(valueCollectSplit[0]).getTime() && keyLong <= sdf.parse(valueCollectSplit[1]).getTime()) {
				return false;
			}
			return true;
			
//		case LIKE:
//			break;
//		case NO_LIKE:
//			break;
		default:
			return false;
		}
	}

	public static void main(String[] args) throws IllegalAccessException, IllegalArgumentException,
			InvocationTargetException, NoSuchMethodException, SecurityException {
	}
}
