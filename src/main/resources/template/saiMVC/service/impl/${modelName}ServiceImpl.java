package ${basePackage}.service.impl;

import com.sai.web.service.impl.PageBaseServiceImpl;
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
public class ${modelName}ServiceImpl extends PageBaseServiceImpl<${modelName}ReqDTO, ${modelName}DTO, ${modelName}DO>
        implements ${modelName}Service {

    @Autowired
    protected ${modelName}Mapper ${modelSmallName}Mapper;

    @Override
    protected ${modelName}Mapper getBaseMapper() {
        return ${modelSmallName}Mapper;
    }

    @Override
    protected ResultCode editValid(${modelName}ReqDTO ${modelSmallName}ReqDTO) {
        if (${modelSmallName}ReqDTO == null) {
            return ResultCode.fail("信息不存在");
        }
        <#list fieldList as item>
            <#if item.fieldDataType == "String">
                String ${item.fieldKey} = ${modelSmallName}ReqDTO.get${item.fieldUpKey}();
                if (StringUtil.isBlank(${item.fieldKey})) {
                    return ResultCode.fail("${item.fieldKey}不能为空");
                }
            <#else>
                ${item.fieldDataType} ${item.fieldKey} = ${modelSmallName}ReqDTO.get${item.fieldUpKey}();
                if (${item.fieldKey} == null) {
                    return ResultCode.fail("${item.fieldKey}错误");
                }
            </#if>
        </#list>

        return ResultCode.success();
    }

    @Override
    protected ${modelName}DTO do2Dto(${modelName}DO ${modelSmallName}DO){
        ${modelName}DTO ${modelName}DTO=new ${modelName}DTO(${modelSmallName}DO);
        return ${modelName}DTO;
    }

    @Override
    protected ${modelName}DO req2Do(${modelName}ReqDTO ${modelName}reqDTO) {
        return null;
    }
}
