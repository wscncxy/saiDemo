package com.sai.demo.service;

import java.util.Properties;

public abstract class SaiSpringMvcInitialize {

    private  String configFilePath;

    private boolean isInitialize = false;

    public abstract void doLoadConfig(String configFilePath);

    public abstract void doScannerSource();

    public abstract void doInstance();

    public abstract void doBuildMappingHandler();

    public void setConfigFilePath(String configFilePath) {
        this.configFilePath = configFilePath;
    }

    public final void init(String configFilePath) {
        if (!isInitialize) {
            synchronized (this) {
                if (!isInitialize) {
                    setConfigFilePath(configFilePath);

                    //读取配置
                    doLoadConfig(configFilePath);

                    //遍历目录，获取所有类
                    doScannerSource();

                    //实例化扫描获得的类
                    doInstance();

                    //组装URLMappingHandler
                    doBuildMappingHandler();

                    finish();
                }
            }
        }
    }

    public void finish(){
        System.out.println("SaiMvc启动成功了");
        isInitialize = true;
    }
}
