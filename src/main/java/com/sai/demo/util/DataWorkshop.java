package com.sai.demo.util;

import lombok.Getter;

import java.lang.ref.WeakReference;
import java.util.HashMap;


public class DataWorkshop {

    @Getter
    private String workshopId;

    private WeakReference<HashMap> userInfo = new WeakReference<>(new HashMap<String, Object>());

    public DataWorkshop(String workshopId){
        this.workshopId = workshopId;
    }

    public HashMap getUserInfo(Object userInfo){
        if(userInfo!=null){
            return (HashMap)userInfo;
        }
        //todo something,这里可以结合责任链获取数据
        return new HashMap<>();
    }
}
