package com.max.util;

import java.io.File;
import java.io.InputStream;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.io.SAXReader;

public class XMLToJavaUtil {
	
	public static Document parse(InputStream runFileInputStream) {
		if (runFileInputStream == null) {
			System.out.println("the runFileInputStream is null");
		}
		SAXReader reader = new SAXReader();
		Document doc;
		try {
			doc = reader.read(runFileInputStream);
			return doc;
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
}
