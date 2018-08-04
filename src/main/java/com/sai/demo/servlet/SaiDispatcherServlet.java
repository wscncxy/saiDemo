package com.sai.demo.servlet;

import com.sai.demo.service.impl.SimpleMappingViewHandler;
import com.sai.demo.service.impl.SimpleMvcInitialize;
import com.sai.demo.service.impl.SimpleRequestMappingHandler;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class SaiDispatcherServlet extends HttpServlet {

    @Override
    public void init(ServletConfig config) throws ServletException {
        SimpleMvcInitialize.getInitialize().init(config.getInitParameter("contextConfigLocation"));
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        try {
            Object requestResult = SimpleRequestMappingHandler.getInitialize().doDispatch(req, resp);
            SimpleMappingViewHandler.getInitialize().doView(requestResult, req, resp);
        } catch (Exception e) {
            e.printStackTrace();
            resp.setStatus(500);
        }
    }
}
