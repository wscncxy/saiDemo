package com.sai.demo.generator.model;


import com.sai.demo.generator.annotation.GenClassBaseInfo;
import com.sai.demo.generator.annotation.GenClassField;

import java.util.Date;

/**
 * by zhouxiang sai
 */

/**
 * modelName: 必须首字母大写
 * 主键数据类型默认为Long
 * 字段名为数据库的字段名，多单词建议用 _ 下划线隔开，解析时会自动转成驼峰，例子：1、show_type ——》showType 2、merchantid ——》merchantid
 * tableName为数据库的表名，如果为空，则默认为modelName，如果非空，多单词建议用 _ 下划线隔开，解析时会自动转成驼峰，例子：1、show_type ——》showType 2、merchantid ——》merchantid
 */
@GenClassBaseInfo(
        modelName = "Banner",
        tableName="",
        basePackage = "com.xianjinxia.cms"
)
public class Banner {

    @GenClassField(comment = "主键")
    private Long id;

    @GenClassField(comment = "图片链接")
    private String url;

    @GenClassField
    private String title;

    @GenClassField
    private String reurl;

    @GenClassField
    private Integer sort;

    @GenClassField
    private Integer status;

    @GenClassField
    private Integer show_type;

    @GenClassField
    private Integer channel_type;

    @GenClassField
    private Integer column_type;

    @GenClassField
    private Date start_time;

    @GenClassField
    private Date end_time;

    @GenClassField
    private Integer present_tay;

    @GenClassField
    private String sort_str;

    @GenClassField
    private String app_version;

    @GenClassField
    private String merchantId;

    @GenClassField
    private Long creat_by;

    @GenClassField
    private Date create_time;

    @GenClassField
    private Long update_by;

    @GenClassField
    private Date update_time;

}
