/**
 * 
 */
package com.neusoft;

import java.security.MessageDigest;
import java.util.HashSet;
import java.util.Set;


public class StringTools {

	public static boolean isEmpty(String str) {
		if (null == str || "".equals(trim(str))) {
			return true;
		} else {
			return false;
		}
	}

	public static String trim(String str) {
		if (str != null) {
			return str.trim();
		} else {
			return "";
		}
	}

	public static String parseString(String str) {
		return trim(str);
	}

	public static String parseNull(String str) {
		String s = parseString(str);
		if ("".equals(s)) {
			return null;
		} else {
			return s;
		}
	}

	public static String objToStr(Object obj) {
		return (obj == null) ? "" : obj.toString();
	}

	public static String[] toDiffArray(String[] s) {
		Set<String> set = new HashSet<String>();
		for (String sa : s) {
			set.add(sa);
		}
		return set.toArray(new String[] {});
	}
	
	public static void main(String[] args) {
		try {
			String s = "[{\"id\":\"2015-07-16 16:53:19.0\",\"timestamp\":\"20150713100538\",\"phone\":\"18712365456\",\"name\":\"张三\",\"password\":\"\",\"cardid\":\"\"}]";
			System.out.println((s));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//System.out.println(getFromBase64("bHNkamZsa3NkamZrbA=="));
	}
	
	 

}
