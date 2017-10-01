package com.neusoft;

import java.text.MessageFormat;
import java.util.ResourceBundle;

public class MessagesTools {
	public static void main(String[] args){
	}
	public static String getMessge(String baseName,String key){
		return ResourceBundle.getBundle(baseName).getString(key);
	}
	public static String getMessge(String baseName,String key,Object[] objs){
		return MessageFormat.format(getMessge(baseName,key), objs);
	}
}
