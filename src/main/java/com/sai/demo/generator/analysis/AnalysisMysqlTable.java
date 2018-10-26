package com.sai.demo.generator.analysis;


import com.sai.demo.generator.constants.Constant;
import com.sai.demo.util.StringUtils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
        modelTemplateDate.put("basePackage", basePackage);
        modelTemplateDate.put("tableName", tableName);

        String modelName = tableName;
        String[] tableNameArray = tableName.split("_");
        if (tableNameArray.length > 1) {
            modelName = tableNameArray[0];
            for (int i = 1; i < tableNameArray.length; i++) {
                modelName += StringUtils.firstToUp(tableNameArray[i]);
            }
        }
        modelTemplateDate.put("modelName", StringUtils.firstToUp(modelName));
        modelTemplateDate.put("modelSmallName", StringUtils.firstToLower(modelName));

        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(dbUrl, user, pwd);
            statement = connection.createStatement();
            resultSet = statement.executeQuery("desc " + tableName);
            List<Map<String, Object>> dataBaseFiedsList = new ArrayList<>();
            boolean hasComment = true;
            while (resultSet.next()) {
                Map<String, Object> dataBaseFiedInfoMap = new HashMap<>();
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
                dataBaseFiedInfoMap.put("columnDataType", columnDataType.toUpperCase());

                String fieldName = columnName;
                String[] fieldNameArray = columnName.split("_");
                if (fieldNameArray.length > 1) {
                    fieldName = fieldNameArray[0];
                    for (int i = 1; i < fieldNameArray.length; i++) {
                        fieldName += StringUtils.firstToUp(fieldNameArray[i]);
                    }
                }

                dataBaseFiedInfoMap.put("fieldUpKey", StringUtils.firstToUp(fieldName));
                dataBaseFiedInfoMap.put("fieldKey", StringUtils.firstToLower(fieldName));
                dataBaseFiedInfoMap.put("fieldDataType", Constant.JDBCTYPEMAPTOJAVATYPE.get(columnDataType.toUpperCase()));

                dataBaseFiedsList.add(dataBaseFiedInfoMap);
            }
            modelTemplateDate.put("fieldList", dataBaseFiedsList);
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
