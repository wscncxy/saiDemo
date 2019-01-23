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
/**
 * goods_category_info
 * goods_category_rela
 * goods_descript444
 * goods_info
 * goods_main
 * goods_specification_rela
 * specification_info
 */
        Analysis analysis = new AnalysisMysqlTable("com.sai.web.area.shopping",
                "jdbc:mysql://db.saiarea.com:3307/shopping?useUnicode=true&characterEncoding=UTF-8&useSSL=false",
                "sai",
                "sai&465412848",
                "goods_descript");
//        Analysis analysis = new AnalysisClass(Banner.class);
        Map<String, Object> dataMap = analysis.analysisIt();
        System.out.println(JSONObject.toJSONString(dataMap));
        if (!dataMap.isEmpty()) {
            new Generator("saiMVC").process(dataMap);
        }
    }
}
