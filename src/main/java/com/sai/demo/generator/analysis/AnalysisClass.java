package com.sai.demo.generator.analysis;


import com.sai.demo.generator.annotation.GenClassBaseInfo;
import com.sai.demo.generator.annotation.GenClassField;
import com.sai.demo.generator.constants.JDBCDataTypeEnum;
import com.sai.demo.util.StringUtils;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AnalysisClass implements Analysis {

    private Class genClass;

    public AnalysisClass(Class genClass) {
        this.genClass = genClass;
    }

    @Override
    public Map<String, Object> analysisIt() {
        Map<String, Object> modelTemplateDate = new HashMap<>();

        GenClassBaseInfo genClassBaseInfo = (GenClassBaseInfo) genClass.getAnnotation(GenClassBaseInfo.class);
        if (genClassBaseInfo != null) {
            Method[] fieldMethods = genClassBaseInfo.getClass().getDeclaredMethods();
            for (Method method : fieldMethods) {
                try {
                    if (method.getParameterTypes().length == 0) {
                        String methodName = method.getName();
                        String methodValue = method.invoke(genClassBaseInfo).toString();
                        modelTemplateDate.put(methodName, methodValue);
                        if ("modelName".equals(methodName)) {
                            modelTemplateDate.put("modelSmallName", StringUtils.firstToLower(methodValue));
                        } else if ("tableName".equals(methodName)) {
                            if (StringUtils.isBlank(methodValue)) {
                                modelTemplateDate.put("tableName", StringUtils.firstToLower(methodValue));
                            }
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            Object tableName = modelTemplateDate.get("tableName");
            if (tableName == null || StringUtils.isBlank(tableName.toString())) {
                modelTemplateDate.put("tableName", modelTemplateDate.get("modelName"));
            }

        }

        //遍历字段
        Field[] fieldArray = genClass.getDeclaredFields();
        List<Map<String, Object>> dataBaseFiedsList = new ArrayList<>();
        if (fieldArray != null && fieldArray.length > 0) {
            for (Field field : fieldArray) {
                Map<String, Object> dataBaseFiedInfoMap = new HashMap<>();

                GenClassField genDateClassTableField = (GenClassField) field.getAnnotation(GenClassField.class);
                if (genDateClassTableField != null) {
                    Method[] fieldMothods = genDateClassTableField.getClass().getDeclaredMethods();
                    for (Method mothod : fieldMothods) {
                        try {
                            if (mothod.getParameterTypes().length == 0) {
                                dataBaseFiedInfoMap.put(mothod.getName(), mothod.invoke(genDateClassTableField).toString());
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }

                String fieldName = field.getName();
                String fieldDataType = field.getType().getSimpleName();
                dataBaseFiedInfoMap.put("columnName", fieldName);
                dataBaseFiedInfoMap.put("columnDataType", JDBCDataTypeEnum.getJdbcType(fieldDataType));
                fieldName = StringUtils.firstToLower(fieldName);
                String[] fieldNameArray = fieldName.split("_");
                if (fieldNameArray.length > 1) {
                    fieldName = fieldNameArray[0];
                    for (int i = 1; i < fieldNameArray.length; i++) {
                        fieldName += StringUtils.firstToUp(fieldNameArray[i]);
                    }
                }

                dataBaseFiedInfoMap.put("fieldUpKey", StringUtils.firstToUp(fieldName));
                dataBaseFiedInfoMap.put("fieldKey", fieldName);
                dataBaseFiedInfoMap.put("fieldDataType", fieldDataType);

                dataBaseFiedsList.add(dataBaseFiedInfoMap);
            }
        }
        modelTemplateDate.put("fieldList", dataBaseFiedsList);
        return modelTemplateDate;
    }


}
