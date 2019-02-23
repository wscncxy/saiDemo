package com.sai.demo.util;

import com.sai.demo.pojo.ScannerSourceInfo;
import org.apache.commons.lang3.StringUtils;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class ScannerSourceUtils {

    public List<ScannerSourceInfo> doScannerSource(String packageName) {
        List<ScannerSourceInfo> scannerSourceInfoList = new ArrayList<>();
        System.out.println(this.getClass().getClassLoader().getResource(""));
        URL packageURL = this.getClass().getClassLoader().getResource("" + packageName.replaceAll("\\.", "/"));
        File file = new File(packageURL.getFile());
        if (file.exists()) {
            for (File subFile : file.listFiles()) {
                if (subFile.isDirectory()) {
                    doScannerSource(packageName + "." + subFile.getName());
                } else {
                    String subFileName = subFile.getName();
                    String className = packageName + "." + subFileName.replace(".class", "");
                    try {
                        ScannerSourceInfo scannerSourceInfo = new ScannerSourceInfo();
                        Class cla=Class.forName(className);
                        scannerSourceInfo.setCla(cla);
                        scannerSourceInfo.setMethod(cla.getMethods());
                        scannerSourceInfoList.add(scannerSourceInfo);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }

        return scannerSourceInfoList;
    }
}
