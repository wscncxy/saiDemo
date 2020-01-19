package com.sai.demo.generator;

import com.alibaba.fastjson.JSONObject;
import com.sai.demo.generator.analysis.Analysis;
import com.sai.demo.generator.analysis.AnalysisMysqlTable;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 模版生成器
 *  fieldUpKey:字段首字母大写,
 *  fieldKey:字段首字母小写
 *  fieldDataType:字段类型
 *  fieldDateTypeFullNameList:字段类型全名，ex：java.lang.String
 */
@Slf4j
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
        List<String> tableList = new ArrayList<>();
        tableList.add("brand_info");
        tableList.add("brand_series_info");
        tableList.add("goods_category_info");
        tableList.add("goods_category_relation");
        tableList.add("goods_descript");
        tableList.add("goods_info");
        tableList.add("goods_main");
        tableList.add("goods_pool");
        tableList.add("goods_pool_relation");
        tableList.add("goods_specification_rela");
        tableList.add("specification_info");
        Generator generator = new Generator("saiMVC",true);
        for (String tableName : tableList) {
            Analysis analysis = new AnalysisMysqlTable("com.sai.web.shopping",
                    "jdbc:mysql://db.saiarea.com:3307/sai_shopping?useUnicode=true&characterEncoding=UTF-8&useSSL=false",
                    "sai",
                    "F8@9eca&kc3fCdas",
                    tableName);
            Map<String, Object> dataMap = analysis.analysisIt();
            log.info(JSONObject.toJSONString(dataMap));
            if (!dataMap.isEmpty()) {
                generator.process(dataMap);
            }
        }
    }
}
