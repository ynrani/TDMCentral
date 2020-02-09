package com.tdm.util;

public class DataGenarationUtils {
	
	public static String getRequestNo(String type, Long seqNo) {
		String value = type + "000000000000"+seqNo;
		Long i =Long.valueOf(value.substring(6));
		value= value.substring(0,15-i.toString().length());
		System.out.println(value);
		value+=i;
		return value;
	}
}
