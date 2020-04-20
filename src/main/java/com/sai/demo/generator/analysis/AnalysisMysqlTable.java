package com.sai.demo.generator.analysis;


import com.sai.demo.generator.constants.Constant;
import com.sai.demo.generator.constants.JavaDataTypeEnum;
import com.sai.demo.generator.constants.JDBCDataTypeEnum;
import com.sai.demo.generator.constants.MybatisDataTypeEnum;
import com.sai.demo.util.StringUtils;
import lombok.extern.slf4j.Slf4j;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.*;

@Slf4j
public class AnalysisMysqlTable implements Analysis {

    private String dbUrl;
    private String user;
    private String pwd;
    private String tableName;
    private String basePackage;

    public AnalysisMysqlTable(String basePackage, String dbUrl, String user, String pwd, String tableName) {
        this.basePackage = basePackage;
        this.dbUrl = dbUrl;
        this.user = user;
        this.pwd = pwd;
        this.tableName = tableName;
    }

    @Override
    public Map<String, Object> analysisIt() {
        Map<String, Object> modelTemplateDate = new HashMap<>();
        modelTemplateDate.put(Constant.GENERAT_PARAM_BASE_PACKAGE, basePackage);
        modelTemplateDate.put(Constant.GENERAT_PARAM_TABLE_NAME, tableName);

        String modelName = tableName;
        String[] tableNameArray = tableName.split("_");
        if (tableNameArray.length > 1) {
            modelName = tableNameArray[0];
            for (int i = 1; i < tableNameArray.length; i++) {
                modelName += StringUtils.firstToUp(tableNameArray[i]);
            }
        }
        modelTemplateDate.put(Constant.GENERAT_PARAM_MODE_NAME, StringUtils.firstToUp(modelName));
        modelTemplateDate.put(Constant.GENERAT_PARAM_MODEL_SMALL_NAME, StringUtils.firstToLower(modelName));

        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(dbUrl, user, pwd);
            statement = connection.createStatement();
            resultSet = statement.executeQuery("desc " + tableName);
            List<Map<String, String>> dataBaseFiedsList = new ArrayList<>();
            Set<String> fieldDateTypeFullNameList = new HashSet<>();
            boolean hasComment = true;
            while (resultSet.next()) {
                Map<String, String> dataBaseFiedInfoMap = new HashMap<>();
                try{
                    if(hasComment){
                        dataBaseFiedInfoMap.put("comment", resultSet.getString("Comment"));
                    }else{
                        dataBaseFiedInfoMap.put("comment", "");
                    }
                }catch (Exception e){
                    dataBaseFiedInfoMap.put("comment", "");
                    hasComment = false;
                }

                String columnName = resultSet.getString("Field");
                columnName = StringUtils.firstToLower(columnName);
                dataBaseFiedInfoMap.put("columnName", columnName);

                String columnDataType = resultSet.getString("Type");
                if (columnDataType.indexOf("(") > 0) {
                    columnDataType = columnDataType.substring(0, columnDataType.indexOf("("));
                }
                columnDataType = columnDataType.toUpperCase();
                String mybatisDataType= MybatisDataTypeEnum.getMybatisTypeByJdbcType(columnDataType);
                columnDataType = StringUtils.isNotBlank(mybatisDataType)?mybatisDataType:columnDataType;
                dataBaseFiedInfoMap.put("columnDataType", columnDataType);

                String fieldName = columnName;
                String[] fieldNameArray = columnName.split("_");
                if (fieldNameArray.length > 1) {
                    fieldName = fieldNameArray[0];
                    for (int i = 1; i < fieldNameArray.length; i++) {
                        fieldName += StringUtils.firstToUp(fieldNameArray[i]);
                    }
                }

                dataBaseFiedInfoMap.put(Constant.GENERAT_PARAM_FIELD_FIRST_UP_KEY, StringUtils.firstToUp(fieldName));
                dataBaseFiedInfoMap.put(Constant.GENERAT_PARAM_FIELD_KEY, StringUtils.firstToLower(fieldName));
                String javaDataType = JDBCDataTypeEnum.getjavaTypeByJdbcType(columnDataType);
                dataBaseFiedInfoMap.put(Constant.GENERAT_PARAM_FIELD_DATA_TYPE, javaDataType);
                if(StringUtils.isBlank(javaDataType)){
                    log.info("{} 没有JavaDatatype",columnDataType);
                }

                fieldDateTypeFullNameList.add(JavaDataTypeEnum.getjavaTypeFullName(dataBaseFiedInfoMap.get(Constant.GENERAT_PARAM_FIELD_DATA_TYPE)));

                dataBaseFiedsList.add(dataBaseFiedInfoMap);
            }
            modelTemplateDate.put(Constant.GENERAT_PARAM_FIELD_LIST, dataBaseFiedsList);
            modelTemplateDate.put(Constant.GENERAT_PARAM_FIELD_DATA_TYPE_FULL_NAME_LIST, fieldDateTypeFullNameList);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (resultSet != null) {
                    resultSet.close();
                }

                if (statement != null) {
                    statement.close();
                }

                if (connection != null) {
                    connection.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return modelTemplateDate;
    }


}
