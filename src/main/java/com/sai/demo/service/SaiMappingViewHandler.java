package com.sai.demo.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface SaiMappingViewHandler {
    void doView(Object mappingResult, HttpServletRequest req, HttpServletResponse resp) throws Exception;
}
