package ${basePackage}.controller;

import ${basePackage}.domain.${modelName};
import ${basePackage}.service.${modelName}Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.sai.web.controller.PageBaseController;
import com.sai.web.service.PageService;
import java.io.IOException;
import java.util.*;

@RestController
@RequestMapping("/${modelSmallName}")
public class ${modelName}Controller extends PageBaseController {

    @Autowired
    private ${modelName}Service ${modelSmallName}Service;

    protected PageService getPageService(){
        return ${modelSmallName}Service;
    }

}
