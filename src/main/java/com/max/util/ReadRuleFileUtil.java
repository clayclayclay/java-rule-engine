//package com.max.util;
//
//import java.io.BufferedReader;
//import java.io.IOException;
//import java.io.InputStream;
//import java.io.InputStreamReader;
//
//import com.max.entity.RuleEntity;
//
//public class ReadRuleFileUtil {
//	
//
//	
//	public static boolean readRuleFile(InputStream in ) throws IOException {
//		BufferedReader br = new BufferedReader(new InputStreamReader(in));
//		RuleEntity ruleEntity = new RuleEntity();
//		String line;
//		while ((line = br.readLine()) != null) {
//			if (!(line.equals("-------------------------END-------------------------")) && line.equals(" ")) {
//				String methodName = "set" + line.split("=")[0].trim();
//				
//				
//			}
//			else {
//				
//			}
//		}
//	}
//}
