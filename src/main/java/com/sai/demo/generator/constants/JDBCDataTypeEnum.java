package com.sai.demo.generator.constants;

import lombok.AllArgsConstructor;

import java.sql.JDBCType;


@AllArgsConstructor
public enum JDBCDataTypeEnum {

    JDBC_DATE_TYPE_INT("INT","Integer"),
    JDBC_DATE_TYPE_INTEGER("INTEGER","Integer"),
    JDBC_DATE_TYPE_LONG("BIGINT","Long"),
    JDBC_DATE_TYPE_STRING("VARCHAR","String"),
    JDBC_DATE_TYPE_DATE("DATETIME","Date"),
    JDBC_DATE_TYPE_TIMESTAMP("TIMESTAMP", "Date"),
    JDBC_DATE_TYPE_BIGDECIMAL("DECIMAL", "BigDecimal"),
    JDBC_DATE_TYPE_BOOLEAN("BIT", "Boolean"),
    JDBC_DATE_TYPE_TINYINT("TINYINT", "Integer"),
    JDBC_DATE_TYPE_SHORT("SMALLINT", "Integer"),
    JDBC_DATE_TYPE_DOUBLE("DOUBLE", "Double"),
    JDBC_DATE_TYPE_FLOAT("FLOAT", "Float"),
    JDBC_DATE_TYPE_TEXT("TEXT", "String"),
    JDBC_DATE_TYPE_MEDIUMTEXT("MEDIUMTEXT", "String");


    private String jdbcType;
    private String javaType;

    public static String getJdbcType(String javaType) {
        for (JDBCDataTypeEnum dataTypeEnum : JDBCDataTypeEnum.values()) {
            if (dataTypeEnum.javaType.equals(javaType)) {
                return dataTypeEnum.jdbcType;
            }
        }
        return null;
    }

    public static String getjavaTypeByJdbcType(String jdbcType) {
        for (JDBCDataTypeEnum dataTypeEnum : JDBCDataTypeEnum.values()) {
            if (dataTypeEnum.jdbcType.equalsIgnoreCase(jdbcType)) {
                return dataTypeEnum.javaType;
            }
        }
        return "";
    }
}
