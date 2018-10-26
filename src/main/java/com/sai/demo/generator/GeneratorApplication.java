package com.sai.demo.generator;

import com.alibaba.fastjson.JSONObject;
import com.sai.demo.generator.analysis.Analysis;
import com.sai.demo.generator.analysis.AnalysisMysqlTable;

import java.util.Map;

/**
 * Hello world!
 */
public class GeneratorApplication {

    public static void main(String[] args) throws Exception {

        Analysis analysis = new AnalysisMysqlTable("com.sai.openapi",
                "jdbc:mysql://db.saiarea.com:3307/saiarea?useUnicode=true&characterEncoding=UTF-8&useSSL=false",
                "sai",
                "sai&465412848",
                "api_router_param");
//        Analysis analysis = new AnalysisClass(Banner.class);
        Map<String, Object> dataMap = analysis.analysisIt();
        System.out.println(JSONObject.toJSONString(dataMap));
        if (!dataMap.isEmpty()) {
            new Generator("saiMVC").process(dataMap);
        }
    }
}
