package com.sai.demo.generator.constants;


import com.sai.demo.util.StringUtils;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class Constant {
    public static Map<String, String> JAVATYPETOJDBCTYPEMAP = new HashMap<>();
    public static Map<String, String> JDBCTYPEMAPTOJAVATYPE = new HashMap<>();
    public static Map<String, String> MYSQLTYPETOJDBCTYPEMAP = new HashMap<>();

    static {
        JAVATYPETOJDBCTYPEMAP.put("int", "INTEGER");
        JAVATYPETOJDBCTYPEMAP.put("Integer", "INTEGER");
        JAVATYPETOJDBCTYPEMAP.put("long", "BIGINT");
        JAVATYPETOJDBCTYPEMAP.put("Long", "BIGINT");
        JAVATYPETOJDBCTYPEMAP.put("String", "VARCHAR");
        JAVATYPETOJDBCTYPEMAP.put("Date", "TIMESTAMP");
        JAVATYPETOJDBCTYPEMAP.put("Timestamp  ", "TIMESTAMP");
        JAVATYPETOJDBCTYPEMAP.put("BigDecimal", "DECIMAL");
        JAVATYPETOJDBCTYPEMAP.put("boolean", "BIT");
        JAVATYPETOJDBCTYPEMAP.put("Boolean", "BIT");
        JAVATYPETOJDBCTYPEMAP.put("byte", "TINYINT");
        JAVATYPETOJDBCTYPEMAP.put("Byte", "TINYINT");
        JAVATYPETOJDBCTYPEMAP.put("short", "SMALLINT");
        JAVATYPETOJDBCTYPEMAP.put("double", "DOUBLE");
        JAVATYPETOJDBCTYPEMAP.put("Double", "DOUBLE");
        JAVATYPETOJDBCTYPEMAP.put("float", "FLOAT");
        JAVATYPETOJDBCTYPEMAP.put("Float", "FLOAT");

        MYSQLTYPETOJDBCTYPEMAP.put("int","INTEGER");
        MYSQLTYPETOJDBCTYPEMAP.put("INT","INTEGER");
        MYSQLTYPETOJDBCTYPEMAP.put("BIG","INTEGER");

        Iterator<Map.Entry<String, String>> entryIterator = JAVATYPETOJDBCTYPEMAP.entrySet().iterator();
        while (entryIterator.hasNext()) {
            Map.Entry<String, String> entry = entryIterator.next();
            JDBCTYPEMAPTOJAVATYPE.put(entry.getValue(), StringUtils.firstToUp(entry.getKey()));
        }
        JDBCTYPEMAPTOJAVATYPE.put("DATETIME","Date");
        JDBCTYPEMAPTOJAVATYPE.put("INT","int");
    }
}
