package com.sai.demo.generator.constants;

import lombok.AllArgsConstructor;


@AllArgsConstructor
public enum MybatisDataTypeEnum {

    MYBATIS_DATE_TYPE_INT("INTEGER","INT"),
    MYBATIS_DATE_TYPE_VARCHAR("VARCHAR", "TEXT"),
    MYBATIS_DATE_TYPE_LONGTEXT("VARCHAR", "LONGTEXT"),
    MYBATIS_DATE_TYPE_TIMESTAMP("TIMESTAMP", "DATETIME"),
    MYBATIS_DATE_TYPE_TEXT("VARCHAR", "TEXT"),
    MYBATIS_DATE_TYPE_MEDIUMTEXT("VARCHAR", "MEDIUMTEXT"),
    ;

    private String mybatisType;
    private String jdbcType;

    public static String getJdbcType(String mybatisType) {
        for (MybatisDataTypeEnum dataTypeEnum : MybatisDataTypeEnum.values()) {
            if (dataTypeEnum.mybatisType.equals(mybatisType)) {
                return dataTypeEnum.jdbcType;
            }
        }
        return null;
    }

    public static String getMybatisTypeByJdbcType(String jdbcType) {
        for (MybatisDataTypeEnum dataTypeEnum : MybatisDataTypeEnum.values()) {
            if (dataTypeEnum.jdbcType.equalsIgnoreCase(jdbcType)) {
                return dataTypeEnum.mybatisType;
            }
        }
        return "";
    }
}
