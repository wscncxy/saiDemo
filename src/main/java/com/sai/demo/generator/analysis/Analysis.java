package com.sai.demo.generator.analysis;

import java.util.Map;

/**
 * modelName 模块名称，首字母大写
 * modelSmallName 模块名称，首字母小写
 * columnName 字段名
 * columnDataType 字段数据类型
 * fieldUpKey 类字段名首字母大写
 * fieldKey  类字段名
 * fieldDataType 类实际数据类型
 */
public interface Analysis {

    //解析目标
    Map<String,Object> analysisIt();
}
