package ${basePackage}.service.impl;

import ${basePackage}.domain.${modelName};
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

    @Override
    public ResultCode<String> add(${modelName} ${modelSmallName}) {
        ResultCode<String> checkResult = check${modelName}(${modelSmallName});
        if (!checkResult.isSuccess()) {
            return checkResult;
        }
        ${modelSmallName}Mapper.insert(${modelSmallName});
        return ResultCode.success();
    }

    @Override
    protected ResultCode<Void> validEditOpt(${modelName}EditDTO ${modelSmallName}EditDTO) {
        if (${modelSmallName}EditDTO == null) {
            return ResultCode.fail("信息不存在");
        }
        <#list fieldList as item>
            <#if item.fieldDataType == "String">
                String ${item.fieldKey} = ${modelSmallName}.get${item.fieldUpKey}();
                if (StringUtil.isBlank(${item.fieldKey})) {
                    return ResultCode.fail("${item.fieldKey}不能为空");
                }
            <#else>
                ${item.fieldDataType} ${item.fieldKey} = ${modelSmallName}.get${item.fieldUpKey}();
                if (${item.fieldKey} == null) {
                    return ResultCode.fail("${item.fieldKey}错误");
                }
            </#if>
        </#list>

        return ResultCode.success();
    }

    @Override
    protected ${modelName}DTO do2dto(${modelName}DTO ${modelSmallName}DO){
        ${modelName}DTO ${modelName}DTO=new ${modelName}DTO(${modelSmallName}DO)
    }
}
