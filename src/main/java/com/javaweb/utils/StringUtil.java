package com.javaweb.utils;

public class StringUtil {
	public static boolean isRequestParamValid(String str) {
		return str != null && !str.trim().isEmpty();
	}
}
