package com.yyk.check;

public class IsNumber {
	public static boolean isnumber(String str){
		for (int i=0;i<str.length();i++){
			if (!Character.isDigit(str.charAt(i))){
				return false;
			}
		}
		return true;
	}
}
