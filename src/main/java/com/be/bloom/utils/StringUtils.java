package com.be.bloom.utils;

public final class StringUtils {

    public static String convertToDatabaseColumn(Boolean attribute) {
        return (attribute != null && attribute) ? "Y" : "N";
    }

    public static Boolean convertToEntityAttribute(String yn) {
        return "Y".equalsIgnoreCase(yn);
    }

}
