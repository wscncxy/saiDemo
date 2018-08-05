package com.sai.demo.controller;

import com.sai.demo.annotation.mvc.SaiController;
import com.sai.demo.annotation.mvc.SaiParam;
import com.sai.demo.annotation.mvc.SaiRequestBody;
import com.sai.demo.annotation.mvc.SaiRequestMapping;

import javax.servlet.http.HttpServletRequest;

@SaiController
@SaiRequestMapping("test/")
public class Test2Controller {

    @SaiRequestMapping("second")
    public String test(@SaiParam("cde") String cde, @SaiRequestBody String body, HttpServletRequest request) {
        return "the Second Controller    cde:" + cde + "<br/>body:" + body + "<br/>headToken:" + request.getHeader("token");
    }
}
