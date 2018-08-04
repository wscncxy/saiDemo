package com.sai.demo.service.impl;

import com.sai.demo.annotation.mvc.SaiParam;
import com.sai.demo.annotation.mvc.SaiRequestBody;
import com.sai.demo.pojo.RequestMappingInfo;
import com.sai.demo.service.SaiRequestMappingHandler;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.ServletInputStream;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class SimpleRequestMappingHandler extends SaiRequestMappingHandler {

    private SimpleRequestMappingHandler() {
    }

    private static class InnerInitialize {
        public static final SimpleRequestMappingHandler INSTANCE = new SimpleRequestMappingHandler();
    }

    public static SimpleRequestMappingHandler getInitialize() {
        return InnerInitialize.INSTANCE;
    }

    @Override
    public Object doDispatch(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String url = request.getRequestURI();
        String contextPath = request.getContextPath();
        url = url.replace(contextPath, "").replaceAll("/+", "/");

        System.out.println("请求路径：" + url);
        if (!requestMappingContext.containsKey(url)) {
            return null;
        }
        RequestMappingInfo requestMappingInfo = requestMappingContext.get(url);
        String className = requestMappingInfo.getClassName();
        if (!classContext.containsKey(className)) {
            throw new Exception("Controller不存在");
        }
        Object classObj = classContext.get(className);
        Class[] argumentArray = requestMappingInfo.getMethod().getParameterTypes();
        Object[] argumentValueArray = new Object[argumentArray.length];
        String charset = getCharset(request);
        Method method = requestMappingInfo.getMethod();
        Annotation[][] parameterAnnotations = method.getParameterAnnotations();
        Map<String, String[]> paramsMap = request.getParameterMap();
        for (int argIndex = 0; argIndex < argumentArray.length; argIndex++) {
            Class argument = argumentArray[argIndex];
            if (ServletRequest.class.isAssignableFrom(argument)) {
                argumentValueArray[argIndex] = request;
            } else if (ServletResponse.class.isAssignableFrom(argument)) {
                argumentValueArray[argIndex] = response;
            } else {
                Annotation[] paramAnnotations = parameterAnnotations[argIndex];
                for (Annotation annotation : paramAnnotations) {
                    if (annotation instanceof SaiRequestBody) {
                        byte[] bodyBytes = readAsBytes(request);
                        if (bodyBytes != null) {
                            if (String.class.getTypeName().equals(argument.getTypeName())) {
                                argumentValueArray[argIndex] = new String(bodyBytes, charset);
                            }
                        }
                    } else if (annotation instanceof SaiParam) {
                        SaiParam argumentAnnotation = (SaiParam) annotation;
                        String annotationValue = argumentAnnotation.value();
                        if (StringUtils.isNotBlank(annotationValue)) {
                            String[] paramValue = paramsMap.get(annotationValue);
                            if (List.class.isAssignableFrom(argument)) {
                                argumentValueArray[argIndex] = Arrays.asList(paramValue);
                            } else if (argument.isArray()) {
                                argumentValueArray[argIndex] = paramValue;
                            } else {
                                argumentValueArray[argIndex] = paramValue[0];
                            }
                        }
                    }
                }
            }
        }
        Object mappingResult = requestMappingInfo.getMethod().invoke(classObj, argumentValueArray);
        return mappingResult;
    }

    // 二进制读取
    public byte[] readAsBytes(HttpServletRequest request) {
        int len = request.getContentLength();
        if (len < 1) {
            return null;
        }
        byte[] buffer = new byte[len];
        ServletInputStream in = null;
        try {
            in = request.getInputStream();
            in.read(buffer, 0, len);
            in.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (null != in) {
                try {
                    in.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return buffer;
    }

    private String getCharset(HttpServletRequest request) {
        String contentType = request.getHeader("Content-type");
        if (contentType != null) {
            String[] paramArray = contentType.split(";");
            if (paramArray.length > 1) {
                return paramArray[1].trim();
            }
        }
        return "UTF-8";
    }

}
