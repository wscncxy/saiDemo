package ${basePackage}.controller;

import ${basePackage}.domain.${modelName};
import ${basePackage}.service.${modelName}Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.sai.web.controller.PageBaseController;
import com.sai.web.service.PageService;
import java.io.IOException;

import ${basePackage}.dto.${modelName}DTO;
import ${basePackage}.dto.${modelName}EditDTO;
import ${basePackage}.dto.${modelName}ReqDTO;

@RestController
@RequestMapping("/${modelSmallName}")
public class ${modelName}Controller extends PageBaseController<${modelName}DTO, ${modelName}EditDTO, ${modelName}ReqDTO> {

    @Autowired
    private ${modelName}Service ${modelSmallName}Service;

    protected PageService getPageService(){
        return ${modelSmallName}Service;
    }

}
