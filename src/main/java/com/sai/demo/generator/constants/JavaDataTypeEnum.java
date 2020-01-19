package com.sai.demo.generator.constants;

import lombok.AllArgsConstructor;


@AllArgsConstructor
public enum JavaDataTypeEnum {

    date_type_int("int", ""),
    date_type_Integer("Integer", "java.lang.Integer"),
    date_type_long("long", ""),
    date_type_Long("Long", "java.lang.Long"),
    date_type_String("String", "java.lang.String"),
    date_type_Date("Date", "java.util.Date"),
    date_type_Timestamp("Timestamp  ", "java.util.Timestamp"),
    date_type_BigDecimal("BigDecimal", "java.math.BigDecimal"),
    date_type_boolean("boolean", ""),
    date_type_Boolean("Boolean", "java.lang.Boolean"),
    date_type_byte("byte", ""),
    date_type_Byte("Byte", "java.lang.Byte"),
    date_type_short("short", ""),
    date_type_double("double", ""),
    date_type_Double("Double", "java.lang.Double"),
    date_type_float("float", ""),
    date_type_Float("Float", "java.lang.Float");

    private String javaType;
    private String javaTypeFullName;

    public static String getjavaTypeFullName(String javaType) {
        for (JavaDataTypeEnum javaDataTypeEnum : JavaDataTypeEnum.values()) {
            if (javaDataTypeEnum.javaType.equals(javaType)) {
                return javaDataTypeEnum.javaTypeFullName;
            }
        }
        return null;
    }
}
