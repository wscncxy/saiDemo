package com.sai.demo.service.impl;

import com.sai.demo.service.SaiMappingViewHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SimpleMappingViewHandler implements SaiMappingViewHandler {
    private SimpleMappingViewHandler() {
    }

    private static class InnerInitialize {
        public static final SimpleMappingViewHandler INSTANCE = new SimpleMappingViewHandler();
    }

    public static SimpleMappingViewHandler getInitialize() {
        return SimpleMappingViewHandler.InnerInitialize.INSTANCE;
    }

    @Override
    public void doView(Object mappingResult, HttpServletRequest req, HttpServletResponse resp) throws Exception {
        if (mappingResult != null) {
            resp.getWriter().write(mappingResult.toString());
            resp.flushBuffer();
        } else {
            resp.setCharacterEncoding("UTF-8");
            resp.setContentType("application/json");
            resp.setStatus(404);
            resp.getWriter().write("页面失踪了");
        }
    }
}
