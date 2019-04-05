package com.softserve.academy.Tips4Trips.utils;

import java.util.HashMap;

public class CheckForXssAttack {
    private static final HashMap<Integer, String> charMap =
            new HashMap<Integer, String>();

    static {
        charMap.put(34, "&quot;");    // double quote
        charMap.put(35, "&#35;");     // hash mark (no HTML named entity)
        charMap.put(38, "&amp;");     // ampersand
        charMap.put(39, "&apos;");    // apostrophe, aka single quote
        charMap.put(60, "&lt;");      // less than
        charMap.put(62, "&gt;");      // greater than
    }

    public static String replaceAllSymbols(String input){
        StringBuilder builder = new StringBuilder();
        char[] charArray = input.toCharArray();
        for (char nextChar : charArray) {
            String entityName = charMap.get((int) nextChar);
            if (entityName == null) {
                if (nextChar > 0x7F)
                    builder.append("&#")
                            .append(Integer.toString(nextChar, 10))
                            .append(";");
                else
                    builder.append(nextChar);
            } else
                builder.append(entityName);
        }
        return builder.toString();
    }
}
