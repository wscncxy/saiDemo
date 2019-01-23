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

import java.util.Date;
import java.util.List;
import java.math.BigDecimal;
import java.util.Map;

@Service
public class ${modelName}ServiceImpl implements ${modelName}Service {

    @Autowired
    private ${modelName}Mapper ${modelSmallName}Mapper;


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
    public ResultCode<String> update(${modelName} ${modelSmallName}) {
        ResultCode<String> checkResult = check${modelName}(${modelSmallName});
        if (!checkResult.isSuccess()) {
            return checkResult;
        }

        BigDecimal id = ${modelSmallName}.getId();
        if (id == null || id.compareTo(NumberContants.BIG_ONE) == -1) {
            return ResultCode.fail("ID错误");
        }

        ${modelName} old${modelName} = ${modelSmallName}Mapper.selectByPrimaryKey(id);
        if (old${modelName} == null) {
            return ResultCode.fail("信息不存在");
        }
        <#list fieldList as item>
            old${modelName}.set${item.fieldUpKey}(${modelSmallName}.get${item.fieldUpKey}());
        </#list>

        ${modelSmallName}Mapper.updateByPrimaryKey(old${modelName});
        return ResultCode.success();
    }

    private ResultCode<String> check${modelName}(${modelName} ${modelSmallName}) {
        if (${modelSmallName} == null) {
            return ResultCode.fail("信息不存在");
        }
        <#list fieldList as item>
            <#if item.fieldDataType == "String">
                String ${item.fieldKey} = ${modelSmallName}.get${item.fieldUpKey}();
                if (StringUtil.isBlank(${item.fieldKey})) {
                    return ResultCode.fail(${item.fieldKey}+"不能为空");
                }
            <#else>
                ${item.fieldDataType} ${item.fieldKey} = ${modelSmallName}.get${item.fieldUpKey}();
                if (${item.fieldKey} == null) {
                    return ResultCode.fail(${item.fieldKey}+"错误");
                }
            </#if>
        </#list>

        return ResultCode.success();
    }

    @Override
    public ResultCode<${modelName}> get(BigDecimal id) {
        if (id == null || id.compareTo(NumberContants.BIG_ONE) == -1) {
            return null;
        }
        return ResultCode.success(${modelSmallName}Mapper.selectByPrimaryKey(id));
    }

    @Override
    public List<${modelName}> list(JSONObject paramJson) {
        List<${modelName}> resultList = ${modelSmallName}Mapper.selectList(paramJson);
        Page page = PageHelper.getLocalPage();
        if (page != null) {
            page.clear();
            page.addAll(resultList);
            return page;
        }
        return resultList;
    }

}
