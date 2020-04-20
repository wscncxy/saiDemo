package ${basePackage}.controller;

import ${basePackage}.service.${modelName}Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.sai.web.controller.BaseController;
import com.sai.web.service.PageService;
import java.io.IOException;

import ${basePackage}.dto.${modelName}DTO;
import ${basePackage}.dto.${modelName}ReqDTO;

@RestController
@RequestMapping("/${modelSmallName}")
public class ${modelName}Controller extends BaseController {

    @Autowired
    private ${modelName}Service ${modelSmallName}Service;

}
