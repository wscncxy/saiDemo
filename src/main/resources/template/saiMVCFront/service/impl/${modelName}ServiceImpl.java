package ${basePackage}.service.impl;

import ${basePackage}.domain.${modelName}DO;
import ${basePackage}.dto.${modelName}ReqDTO;
import ${basePackage}.dto.${modelName}DTO;
import ${basePackage}.mapper.${modelName}Mapper;
import ${basePackage}.service.${modelName}Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.sai.core.constants.NumberContants;
import com.sai.core.dto.ResultCode;
import com.sai.core.utils.StringUtil;
import com.alibaba.fastjson.JSONObject;

<#list fieldDateTypeFullNameList as item>
        <#if item?? && item!="">
        import ${item};
        </#if>
</#list>

@Service
public class ${modelName}ServiceImpl implements ${modelName}Service {

    @Autowired
    protected ${modelName}Mapper ${modelSmallName}Mapper;

    private ${modelName}DTO do2Dto(${modelName}DO ${modelSmallName}DO){
        ${modelName}DTO ${modelName}DTO=new ${modelName}DTO(${modelSmallName}DO);
        return ${modelName}DTO;
        }

    private  ${modelName}DO req2Do(${modelName}ReqDTO ${modelName}reqDTO) {
        return null;
        }
}
