package com.sai.demo.service;

import com.sai.demo.pojo.RequestMappingInfo;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

/**
 * 解析请求路径到指定方法，并返回Controller处理结果
 */
public abstract class SaiRequestMappingHandler {
    protected Map<String, RequestMappingInfo> requestMappingContext = new HashMap<>();
    protected Map<String, Object> classContext = new HashMap<>();

    public void setRequestMappingContext(Map<String, RequestMappingInfo> requestMappingContext) {
        this.requestMappingContext = requestMappingContext;
    }

    public Map<String, Object> getClassContext() {
        return classContext;
    }

    public void setClassContext(Map<String, Object> classContext) {
        this.classContext = classContext;
    }

    public abstract Object doDispatch(HttpServletRequest req, HttpServletResponse resp) throws Exception;
}
