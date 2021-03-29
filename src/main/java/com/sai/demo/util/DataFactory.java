package com.sai.demo.util;

import java.lang.instrument.Instrumentation;

public class DataFactory {

    private static ThreadLocal<DataWorkshop> dataWorkshop = new ThreadLocal<>();

    /**
     * 获取 数据仓库
     * @param workshopId
     * @return
     */
    private DataWorkshop getDataWorkshop(String workshopId) {
        DataWorkshop curWarehouse = dataWorkshop.get();
        //判断当前线程池中的对象是否指定对象
        if (curWarehouse == null
                || (!workshopId.equals(curWarehouse.getWorkshopId()))) {
            //如果不存在或非当前ID，则刷新ThreadLocal中对象
            curWarehouse = new DataWorkshop(workshopId);
            dataWorkshop.set(curWarehouse);
        }
        return curWarehouse;
    }

}
