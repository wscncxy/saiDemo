package com.sai.demo.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.sai.demo.annotation.mvc.SaiController;
import com.sai.demo.annotation.mvc.SaiRequestMapping;
import com.sai.demo.pojo.RequestMappingInfo;
import com.sai.demo.service.SaiRequestMappingHandler;
import com.sai.demo.service.SaiSpringMvcInitialize;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.http.HttpServlet;
import java.io.File;
import java.io.InputStream;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.net.URL;
import java.util.*;

public class SimpleMvcInitialize extends SaiSpringMvcInitialize {

    private SimpleMvcInitialize() {
    }

    private static class InnerInitialize {
        public static final SimpleMvcInitialize INSTANCE = new SimpleMvcInitialize();
    }

    public static SimpleMvcInitialize getInitialize() {
        return InnerInitialize.INSTANCE;
    }

    private String baseUrl = "";
    private Properties properties = new Properties();
    private List<String> classNameList = new ArrayList<>();

    @Override
    public void doLoadConfig(String configFilePath) {
        InputStream resourceAsStream = null;
        try {
            resourceAsStream = this.getClass().getClassLoader().getResourceAsStream(configFilePath);
            properties.load(resourceAsStream);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (resourceAsStream != null) {
                try {
                    resourceAsStream.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Override
    public void doScannerSource() {
        String scanPackage = properties.getProperty("sai.scanPackage");
        baseUrl = properties.getProperty("sai.baseUrl");
        if (StringUtils.isBlank(baseUrl)) {
            baseUrl = "/";
        }
        if (StringUtils.isNotBlank(scanPackage)) {
            System.out.println("开始扫描：" + scanPackage);
            doScannerSource(scanPackage);
        }
    }

    private void doScannerSource(String packageName) {
        URL packageURL = this.getClass().getClassLoader().getResource("/" + packageName.replaceAll("\\.", "/"));
        File file = new File(packageURL.getFile());
        if (file.exists()) {
            for (File subFile : file.listFiles()) {
                if (subFile.isDirectory()) {
                    doScannerSource(packageName + "." + subFile.getName());
                } else {
                    String subFileName = subFile.getName();
                    String className = packageName + "." + subFileName.replace(".class", "");
                    classNameList.add(className);
                }
            }
        }
    }

    @Override
    public void doInstance() {
        if (classNameList != null && classNameList.size() > 0) {
            SaiRequestMappingHandler saiRequestMappingHandler = SimpleRequestMappingHandler.getInitialize();
            Map<String, Object> classContext = saiRequestMappingHandler.getClassContext();
            for (String className : classNameList) {
                try {
                    Class c = Class.forName(className);
                    int modifiers = c.getModifiers();
                    if (!((!c.isAnnotationPresent(SaiController.class)) ||
                            c.getConstructors().length < 1 ||
                            Modifier.isPrivate(modifiers) ||
                            Modifier.isAbstract(modifiers) ||
                            HttpServlet.class.isAssignableFrom(c))) {
                        classContext.put(className, c.newInstance());
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Override
    public void doBuildMappingHandler() {
        SaiRequestMappingHandler saiRequestMappingHandler = SimpleRequestMappingHandler.getInitialize();
        Map<String, Object> classContext = saiRequestMappingHandler.getClassContext();
        if (!classContext.isEmpty()) {
            try {
                Map<String, RequestMappingInfo> requestMappingContext = new HashMap<>();
                Iterator<Map.Entry<String, Object>> iterator = classContext.entrySet().iterator();
                while (iterator.hasNext()) {
                    Map.Entry<String, Object> entry = iterator.next();
                    String className = entry.getKey();
                    Object classObj = entry.getValue();
                    Class c = classObj.getClass();
                    if (!c.isAnnotationPresent(SaiController.class)) {
                        continue;
                    }
                    String mappingBaseUrl = baseUrl;
                    if (c.isAnnotationPresent(SaiRequestMapping.class)) {
                        SaiRequestMapping requestMappingAnnotation = (SaiRequestMapping) c.getAnnotation(SaiRequestMapping.class);
                        String methodUrl = requestMappingAnnotation.value();
                        if (methodUrl.startsWith("//")) {
                            methodUrl = methodUrl.replaceFirst("//", "");
                        }
                        mappingBaseUrl += methodUrl;
                    }

                    Method[] methodArray = c.getMethods();
                    if (methodArray.length > 0) {
                        for (Method method : methodArray) {
                            if (!method.isAnnotationPresent(SaiRequestMapping.class)) {
                                continue;
                            }
                            SaiRequestMapping requestMappingAnnotation = (SaiRequestMapping) method.getAnnotation(SaiRequestMapping.class);
                            String methodUrl = requestMappingAnnotation.value();
                            if (methodUrl.startsWith("//")) {
                                methodUrl = methodUrl.replaceFirst("//", "");
                            }
                            String mappingUrl = mappingBaseUrl + methodUrl;
                            if (requestMappingContext.containsKey(mappingUrl)) {
                                RequestMappingInfo oldRequestMappingInfo = requestMappingContext.get(mappingUrl);
                                System.out.println("当前路径已经存在：" + className + "->" + oldRequestMappingInfo.getMethod().getName());
                                continue;
                            }
                            RequestMappingInfo requestMappingInfo = new RequestMappingInfo();
                            requestMappingInfo.setClassName(className);
                            requestMappingInfo.setMethod(method);
                            requestMappingInfo.setArguments(method.getParameterTypes());
                            requestMappingContext.put(mappingUrl, requestMappingInfo);
                        }
                    }
                }
                System.out.println(JSONObject.toJSONString(requestMappingContext));
                SimpleRequestMappingHandler.getInitialize().setRequestMappingContext(requestMappingContext);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void init(String configFilePath) {
        setConfigFilePath(configFilePath);
        super.init();
    }


}
