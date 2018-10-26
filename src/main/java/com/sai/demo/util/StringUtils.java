package com.sai.demo.util;

public class StringUtils extends org.apache.commons.lang3.StringUtils {

    public static String firstToUp(String str) {
        if (org.apache.commons.lang3.StringUtils.isNotBlank(str) && str.length() > 1) {
            return str.substring(0, 1).toUpperCase() + str.substring(1);
        }
        return str;
    }

    public static String firstToLower(String str) {
        if (org.apache.commons.lang3.StringUtils.isNotBlank(str) && str.length() > 1) {
            return str.substring(0, 1).toLowerCase() + str.substring(1);
        }
        return str;
    }
}
