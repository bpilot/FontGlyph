package com.radicalninja.fontglyphsample.util;

public class StringUtils {

    public static String beautifyString(final String string) {

        StringBuilder sb = new StringBuilder();
        char prevChar = '_';
        for (int i = 0; i < string.length(); i++) {
            String c = String.valueOf(string.charAt(i));
            if (prevChar == '_') {
                sb.append(c.toUpperCase());
            } else if (c.equals("_")) {
                sb.append(" ");
            } else {
                sb.append(c.toLowerCase());
            }
            prevChar = string.charAt(i);
        }

        return sb.toString();
    }
}
