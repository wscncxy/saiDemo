package com.sai.demo.controller;

import com.sai.demo.annotation.mvc.SaiController;
import com.sai.demo.annotation.mvc.SaiParam;
import com.sai.demo.annotation.mvc.SaiRequestBody;
import com.sai.demo.annotation.mvc.SaiRequestMapping;

import javax.servlet.http.HttpServletRequest;

@SaiController
@SaiRequestMapping("test/")
public class TestController {

    @SaiRequestMapping("first")
    public String test(@SaiParam("abc") String abc, @SaiRequestBody String body, HttpServletRequest request) {
        return "abc:" + abc + "<br/>body:" + body + "<br/>headToken:" + request.getHeader("token");
    }
}
