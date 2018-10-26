package com.sai.demo.util;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Properties;

public class PropertiesUtils extends Properties{
    private final String classRootPath = PropertiesUtils.class.getResource("/").getPath();

    public PropertiesUtils() {
        try {
            // 使用ClassLoader加载properties配置文件生成对应的输入流
            BufferedReader in = new BufferedReader(new FileReader(classRootPath+"/generatorConfig.properties"));
            // 使用properties对象加载输入流
            this.load(in);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
