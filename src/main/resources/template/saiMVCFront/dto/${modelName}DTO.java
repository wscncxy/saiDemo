package ${basePackage}.domain;

import com.sai.web.dto.BaseDTO;
import lombok.Getter;
import lombok.Setter;
<#list fieldDateTypeFullNameList as item>
        <#if item?? && item!="">
import ${item};
</#if>
</#list>

@Setter
@Getter
public class ${modelName}DTO extends BaseDTO{

    public ${modelName}DTO(){}

    public ${modelName}DTO(${modelName}DO ${modelSmallName}){
        <#list fieldList as item>
            set${item.fieldUpKey}(${modelSmallName}.get${item.fieldUpKey}());
        </#list>
    }
<#list fieldList as item>
    private ${item.fieldDataType} ${item.fieldKey};<#if item.comment?? && item.comment!="">//item.comment</#if>
</#list>

}