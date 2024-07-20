package com.javaweb.utils;

public class StringUtil {
	public static boolean isValidRequestParam(String str) {
		return str != null && !str.trim().isEmpty();
	}
}
