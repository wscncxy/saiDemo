package com.sai.demo.util;

import java.lang.instrument.Instrumentation;

public class DataFactory {

    private static ThreadLocal<DataWorkshop> dataWorkshop = new ThreadLocal<>();

    static Instrumentation inst;

    public static void premain(String agentArgs, Instrumentation instP) {
        inst = instP;
    }
    /**
     * 获取 数据仓库
     * @param workshopId
     * @return
     */
    private DataWorkshop getDataWorkshop(String workshopId) {
        DataWorkshop curWarehouse = dataWorkshop.get();
        if (curWarehouse == null
                || (!workshopId.equals(curWarehouse.getWorkshopId()))) {
            curWarehouse = new DataWorkshop(workshopId);
            dataWorkshop.set(curWarehouse);
        }
        inst.getObjectSize(curWarehouse);
        return curWarehouse;
    }

}
